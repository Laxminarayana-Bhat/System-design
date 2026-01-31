package org.example.notifications;

import java.util.Set;

public class User {
    private Integer userId;
    private Set<String> chosenChannels;

    public Integer getUserId() {
        return userId;
    }

    public User(Integer userId, Set<String> chosenChannels) {
        this.userId = userId;
        this.chosenChannels = chosenChannels;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<String> getChosenChannels() {
        return chosenChannels;
    }

    public void setChosenChannels(Set<String> chosenChannels) {
        this.chosenChannels = chosenChannels;
    }
}
