package org.example.notifications;

public interface NotificationSender {

    // strategy pattern
    void send(Notification notification);
}
