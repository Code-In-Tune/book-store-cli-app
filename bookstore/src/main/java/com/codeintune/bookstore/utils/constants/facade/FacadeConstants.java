package com.codeintune.bookstore.utils.constants.facade;

import java.util.List;

public class FacadeConstants {

    public static final String ADD_BOOK_MESSAGE = "1 - Add Book";

    public static final String QUIT_MESSAGE = "q - Quit";


    public static final String ENTER_VALID_INPUT_MESSAGE = "Please enter valid input";


    public static final String ADD_BOOK_OPTION = "1";
    public static final String QUIT_OPTION = "q";

    public static final List<String> LIST_OPTION = List.of(ADD_BOOK_OPTION, QUIT_OPTION);


    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public static final String NEW_LINE_CHARACTER = "\n";

    public static final String SEPARATOR = "-".repeat(40);
    public static final String OPERATION_SUCCESSFUL = "Operation successful";
    public static final String OPERATION_FAILED = "Operation failed";

    public static final String MESSAGE_COMPLETED = GREEN + SEPARATOR.concat(NEW_LINE_CHARACTER).concat(OPERATION_SUCCESSFUL).concat(NEW_LINE_CHARACTER).concat(SEPARATOR) + RESET;
    public static final String MESSAGE_FAILURE = RED +  SEPARATOR.concat(NEW_LINE_CHARACTER).concat(OPERATION_FAILED).concat(NEW_LINE_CHARACTER).concat(SEPARATOR) + RESET;



}
