package com.codeintune.bookstore.exception;

public class OperationAbortedException extends RuntimeException{

    public OperationAbortedException(String message){
        super(message);
    }
}
