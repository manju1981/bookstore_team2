package com.idfc.bootcamp.bookstore.exceptions;

public enum ApiErrors {
    BOOK_ALREADY_EXIST("BOOK-001", "book already exist : %s"),
    INVALID_PAGE("BOOK-002", "invalid page selected : %s"),
    BOOK_NOT_FOUND("BOOK-003", "book not found : %s"),
    INIT_VALIDATION_FAILED("BOOK-004", "Validation failed for order id : %s");
    private final String code;
    private final String message;

    ApiErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
