package org.example.notifications;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        notificationService.notifyUser(
                new User(1, Set.of("EMAIL")),
                "Account credited by $1M, press the link below to redeem");
    }
}
