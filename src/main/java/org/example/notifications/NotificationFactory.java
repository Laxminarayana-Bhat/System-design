package org.example.notifications;


public class NotificationFactory {
    public static NotificationSender getChannel(String channel) {
        return switch (channel) {
            case "EMAIL" -> new EmailNotificationService();
            case "SMS" -> new SmsNotificationService();
            default -> throw new IllegalArgumentException("Invalid channel type");
        };
    }
}
