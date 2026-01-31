package org.example.notifications;


public class Notification {
    private Integer uid;
    private String message;

    public Notification(Integer uid, String message) {
        this.uid = uid;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
