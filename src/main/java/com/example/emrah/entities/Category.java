package com.example.emrah.entities;
public enum Category {
    TAMAMLAYICI_SAGLIK_SIGORTASI(false),
    OZEL_SAGLIK_SIGORTASI(false),
    HAYAT_SIGORTASI(true),
    DIGER_SIGORTALAR(false);


    private boolean status;

    Category(boolean status) {
        this.status = status;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
