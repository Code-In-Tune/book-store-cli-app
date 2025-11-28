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
        boolean quit = false;
        while (!quit) {

            System.out.println(FacadeConstants.ADD_BOOK_MESSAGE);
            System.out.println(FacadeConstants.QUIT_MESSAGE);

            String option = SCANNER.nextLine();
            if (!FacadeConstants.LIST_OPTION.contains(option.trim())) {
                System.out.println(FacadeConstants.ENTER_VALID_INPUT_MESSAGE);
                continue;
            }
            if (option.equals(FacadeConstants.QUIT_OPTION)) {
                quit = true;
            } else if (option.equals(FacadeConstants.ADD_BOOK_OPTION)) {
                String message = bookCliView.addBook();
                System.out.println(message);
            }
        }
    }
}
