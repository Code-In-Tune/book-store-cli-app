package com.codeintune.bookstore.model.book.enums;

import lombok.Getter;

@Getter
public enum Availability {
    IN_STOCK("In stock")
    , NOT_IN_STOCK("Out of stock");

    private final String description;

    Availability(String description) {
        this.description = description;
    }
}
