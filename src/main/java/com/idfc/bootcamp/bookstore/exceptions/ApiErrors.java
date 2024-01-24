package com.idfc.bootcamp.bookstore.exceptions;

public enum ApiErrors {
    BOOK_ALREADY_EXIST("BOOK-001", "book already exist : %s"),
    INVALID_PAGE("BOOK-002", "invalid page selected : %s"),
    BOOK_NOT_FOUND("BOOK-003", "book not found : %s");
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
