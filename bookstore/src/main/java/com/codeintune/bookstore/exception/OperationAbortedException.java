package com.codeintune.bookstore.exception;


/**
 * Operation aborted exception thrown whenever an operation is being canceled
 * due to user input
 */
public class OperationAbortedException extends RuntimeException{

    public OperationAbortedException(String message){
        super(message);
    }
}
