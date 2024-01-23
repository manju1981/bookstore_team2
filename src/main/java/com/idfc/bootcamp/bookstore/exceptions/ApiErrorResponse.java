package com.idfc.bootcamp.bookstore.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiErrorResponse {
    private final String guid;
    private final String errorCode;
    private final String message;
    private final Integer statusCode;
    private final String statusName;
    private final String path;
    private final String method;
    private final LocalDateTime timestamp;
}