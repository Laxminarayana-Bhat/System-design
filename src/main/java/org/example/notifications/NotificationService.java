package org.example.notifications;


public class NotificationService {

    public void notifyUser(User user, String msg) {

        Notification notification = new Notification(user.getUserId(), msg);

        for (String s : user.getChosenChannels()) {
            NotificationSender notificationSender = NotificationFactory.getChannel(s);
            notificationSender.send(notification);
        }
    }

}
