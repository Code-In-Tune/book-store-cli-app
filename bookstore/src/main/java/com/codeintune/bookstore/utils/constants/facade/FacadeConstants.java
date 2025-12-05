package com.codeintune.bookstore.utils.constants.facade;

public class FacadeConstants {

    public static final String ADD_BOOK_MESSAGE = "1 - Add Book";
    public static final String GET_BOOK_MESSAGE = "2 - Get Book By Record Id";
    public static final String GET_BOOKS_BY_AUTHOR_MESSAGE = "3 - Get Books By Author";
    public static final String GET_BOOKS_BY_TITLE_MESSAGE = "4 - Get Books By Title";
    public static final String UPDATE_BOOK_MESSAGE = "5 - Update Book By Record Id";
    public static final String ADD_BOOK_QUANTITY_MESSAGE = "6 - Add Book Quantity By Record Id";
    public static final String REMOVE_BOOK_BY_ID_MESSAGE = "7 - Remove Book By Record Id";
    public static final String REGISTER_SALE_MESSAGE = "8 - Register Sale";
    public static final String SHOW_SALES_MESSAGE = "9 - Show Sales";

    public static final String QUIT_MESSAGE = "q - Quit";


    public static final String ENTER_VALID_INPUT_MESSAGE = "Please enter valid input";


    public static final String ADD_BOOK_OPTION = "1";
    public static final String GET_BOOK_BY_ID_OPTION = "2";
    public static final String GET_BOOKS_BY_AUTHOR_OPTION = "3";
    public static final String GET_BOOKS_BY_TITLE_OPTION = "4";
    public static final String UPDATE_BOOK_OPTION = "5";
    public static final String ADD_BOOK_QUANTITY_OPTION = "6";
    public static final String REMOVE_BOOK_BY_ID_OPTION = "7";
    public static final String REGISTER_SALE_OPTION = "8";
    public static final String SHOW_SALES_OPTION = "9";
    public static final String QUIT_OPTION = "q";


    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public static final String NEW_LINE_CHARACTER = "\n";

    public static final String SEPARATOR = "-".repeat(40);
    public static final String OPERATION_SUCCESSFUL = "Operation successful";
    public static final String OPERATION_FAILED = "Operation failed";
    public static final String START_STORE_MESSAGE = "Welcome To Book Store";

    public static final String MESSAGE_COMPLETED = GREEN + SEPARATOR.concat(NEW_LINE_CHARACTER).concat(OPERATION_SUCCESSFUL).concat(NEW_LINE_CHARACTER).concat(SEPARATOR) + RESET;
    public static final String MESSAGE_FAILURE = RED +  SEPARATOR.concat(NEW_LINE_CHARACTER).concat(OPERATION_FAILED).concat(NEW_LINE_CHARACTER).concat(SEPARATOR) + RESET;

    public static final String OPENING_MESSAGE = SEPARATOR.concat(NEW_LINE_CHARACTER).concat(START_STORE_MESSAGE).concat(NEW_LINE_CHARACTER).concat(SEPARATOR).concat(NEW_LINE_CHARACTER);

    public static final String CLOSING_MESSAGE = "Thanks for using the Book Store!";
}
