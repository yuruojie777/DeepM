package com.ele5620.deepm.util;

import com.ele5620.deepm.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User forumUser) {
        users.set(forumUser);
    }
    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
