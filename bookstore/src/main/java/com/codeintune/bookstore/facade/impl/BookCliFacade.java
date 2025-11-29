package com.codeintune.bookstore.facade.impl;

import com.codeintune.bookstore.facade.CliFacade;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.BookCliView;
import com.codeintune.bookstore.view.SaleCliView;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;


@RequiredArgsConstructor
public class BookCliFacade implements CliFacade {

    public final Scanner scanner;

    private final BookCliView bookCliView;
    private final SaleCliView saleCliView;

    @Override
    public void startStore() {

        System.out.println(FacadeConstants.OPENING_MESSAGE);

        boolean quit = false;
        while (!quit) {

            System.out.println(FacadeConstants.ADD_BOOK_MESSAGE);
            System.out.println(FacadeConstants.GET_BOOK_MESSAGE);
            System.out.println(FacadeConstants.GET_BOOKS_BY_AUTHOR_MESSAGE);
            System.out.println(FacadeConstants.GET_BOOKS_BY_TITLE_MESSAGE);
            System.out.println(FacadeConstants.UPDATE_BOOK_MESSAGE);
            System.out.println(FacadeConstants.ADD_BOOK_QUANTITY_MESSAGE);
            System.out.println(FacadeConstants.REMOVE_BOOK_BY_ID_MESSAGE);
            System.out.println(FacadeConstants.REGISTER_SALE_MESSAGE);
            System.out.println(FacadeConstants.QUIT_MESSAGE);

            String option = scanner.nextLine().trim();
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
                case FacadeConstants.GET_BOOKS_BY_TITLE_OPTION:{
                    String message = bookCliView.getByTitle();
                    System.out.println(message);
                    break;
                }
                case FacadeConstants.UPDATE_BOOK_OPTION:{
                    String message = bookCliView.updateById();
                    System.out.println(message);
                    break;
                }
                case FacadeConstants.ADD_BOOK_QUANTITY:{
                    String message = bookCliView.addBookQuantity();
                    System.out.println(message);
                    break;
                }
                case FacadeConstants.REMOVE_BOOK_BY_ID_OPTION:{
                    String message = bookCliView.removeBookById();
                    System.out.println(message);
                    break;
                }
                case FacadeConstants.REGISTER_SALE_OPTION: {
                    String message = saleCliView.registerSale();
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
