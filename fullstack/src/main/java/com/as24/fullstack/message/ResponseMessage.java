package com.as24.fullstack.message;


public class ResponseMessage {
    /**
     * Output Rest response format model
     * @return formatted string
     */

    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
