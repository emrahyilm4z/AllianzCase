package com.example.emrah.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super(Message.CATEGORYNOTFOUNDID);
    }
}
