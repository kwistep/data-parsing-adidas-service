package com.apparelshop.dataparsingadidasservice.exception;

public class EmptyResponseException extends Throwable {

    private String message;

    public EmptyResponseException() {
        this.message = "Response is empty -> JSON is null";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
