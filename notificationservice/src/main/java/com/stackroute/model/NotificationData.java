package com.stackroute.model;

public class NotificationData {
    private String subject;
    private String message;

    public NotificationData() {
    }

    public NotificationData(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
