package org.example.notifications;


public class NotificationFactory {
    public static NotificationSender getChannel(String channel) {

        // factory pattern
        return switch (channel) {
            case "EMAIL" -> new EmailNotificationService();
            case "SMS" -> new SmsNotificationService();
            default -> throw new IllegalArgumentException("Invalid channel type");
        };
    }
}
