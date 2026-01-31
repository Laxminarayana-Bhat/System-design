package org.example.notifications;

public class SmsNotificationService implements NotificationSender{
    @Override
    public void send(Notification notification) {
        System.out.println("Sending SMS to: " + notification.getUid() + "\nMessage: " + notification.getMessage());
    }
}
