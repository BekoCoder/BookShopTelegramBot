package com.example.bookshopbot.exceptions;

public class BooksNotFoundException extends RuntimeException {
    public BooksNotFoundException(String message) {
        super(message);
    }
}
