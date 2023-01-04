package com.example.emrah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CampaingNotFoundException extends RuntimeException {
    public CampaingNotFoundException() {
        super(Message.CAMPAIGNNOTFOUNDID);
    }
}
