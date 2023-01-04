package com.example.emrah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotChangeableException extends RuntimeException {

    public NotChangeableException() {
        super(Message.NOTCHANGEABLE);
    }
}
