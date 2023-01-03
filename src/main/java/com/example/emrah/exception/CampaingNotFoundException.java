package com.example.emrah.exception;

public class CampaingNotFoundException extends RuntimeException {
    public CampaingNotFoundException(){
        super(Message.CAMPAIGNNOTFOUNDID);
    }
}
