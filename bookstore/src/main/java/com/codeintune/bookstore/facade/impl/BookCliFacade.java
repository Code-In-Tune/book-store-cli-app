package com.codeintune.bookstore.facade.impl;

import com.codeintune.bookstore.facade.CliFacade;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.BookCliView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class BookCliFacade implements CliFacade {

    public static final Scanner SCANNER = new Scanner(System.in);

    private final BookCliView bookCliView;

    @Override
    public void startStore() {

        System.out.println(FacadeConstants.OPENING_MESSAGE);

        boolean quit = false;
        while (!quit) {

            System.out.println(FacadeConstants.ADD_BOOK_MESSAGE);
            System.out.println(FacadeConstants.GET_BOOK_MESSAGE);
            System.out.println(FacadeConstants.GET_BOOKS_BY_AUTHOR_MESSAGE);
            System.out.println(FacadeConstants.QUIT_MESSAGE);

            String option = SCANNER.nextLine().trim();
            switch (option) {
                case FacadeConstants.ADD_BOOK_OPTION: {
                    String message = bookCliView.addBook();
                    System.out.println(message);
                    break;
                }
                case FacadeConstants.GET_BOOK_BY_ID_OPTION: {
                    String message = bookCliView.getBookById();
                    System.out.println(message);
                    break;
                }
                case FacadeConstants.GET_BOOKS_BY_AUTHOR_OPTION:{
                    String message = bookCliView.getByAuthor();
                    System.out.println(message);
                    break;
                }
                case FacadeConstants.QUIT_OPTION: {
                    quit = true;
                    break;
                }
                default: {
                    System.out.println(FacadeConstants.ENTER_VALID_INPUT_MESSAGE);
                    break;
                }
            }
        }
    }
}
