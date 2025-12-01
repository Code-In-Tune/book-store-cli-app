package com.codeintune.bookstore.facade.impl;

import com.codeintune.bookstore.facade.CliFacade;
import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.BookCliView;
import com.codeintune.bookstore.view.SaleCliView;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class BookCliFacade implements CliFacade {


    private final BookStoreInputReader bookStoreInputReader;
    private final BookCliView bookCliView;
    private final SaleCliView saleCliView;
    private final BookStorePrinter bookStorePrinter;

    @Override
    public void startStore() {

        bookStorePrinter.print(FacadeConstants.OPENING_MESSAGE);

        boolean quit = false;
        while (!quit) {

            bookStorePrinter.print(FacadeConstants.ADD_BOOK_MESSAGE);
            bookStorePrinter.print(FacadeConstants.GET_BOOK_MESSAGE);
            bookStorePrinter.print(FacadeConstants.GET_BOOKS_BY_AUTHOR_MESSAGE);
            bookStorePrinter.print(FacadeConstants.GET_BOOKS_BY_TITLE_MESSAGE);
            bookStorePrinter.print(FacadeConstants.UPDATE_BOOK_MESSAGE);
            bookStorePrinter.print(FacadeConstants.ADD_BOOK_QUANTITY_MESSAGE);
            bookStorePrinter.print(FacadeConstants.REMOVE_BOOK_BY_ID_MESSAGE);
            bookStorePrinter.print(FacadeConstants.REGISTER_SALE_MESSAGE);
            bookStorePrinter.print(FacadeConstants.SHOW_SALES_MESSAGE);
            bookStorePrinter.printQuitOption();

            String option = bookStoreInputReader.readNextLine();
            switch (option) {
                case FacadeConstants.ADD_BOOK_OPTION: {
                    String message = bookCliView.addBook();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.GET_BOOK_BY_ID_OPTION: {
                    String message = bookCliView.getBookById();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.GET_BOOKS_BY_AUTHOR_OPTION:{
                    String message = bookCliView.getByAuthor();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.GET_BOOKS_BY_TITLE_OPTION:{
                    String message = bookCliView.getByTitle();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.UPDATE_BOOK_OPTION:{
                    String message = bookCliView.updateById();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.ADD_BOOK_QUANTITY:{
                    String message = bookCliView.addBookQuantity();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.REMOVE_BOOK_BY_ID_OPTION:{
                    String message = bookCliView.removeBookById();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.REGISTER_SALE_OPTION: {
                    String message = saleCliView.registerSale();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.SHOW_SALES_OPTION: {
                    String message = saleCliView.showSales();
                    bookStorePrinter.print(message);
                    break;
                }
                case FacadeConstants.QUIT_OPTION: {
                    quit = true;
                    break;
                }
                default: {
                    bookStorePrinter.print(FacadeConstants.ENTER_VALID_INPUT_MESSAGE);
                    break;
                }
            }
        }
    }
}
