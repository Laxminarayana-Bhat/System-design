package org.example.notifications;

public class EmailNotificationService implements NotificationSender {
    @Override
    public void send(Notification notification) {
        System.out.println("Sending Email to: " + notification.getUid() + "\nMessage: " + notification.getMessage());
    }
}
