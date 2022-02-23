package com.crystal.ovs.appRunner;

import com.crystal.ovs.dao.User;

public class ClientAppRunner {
    private User user;

    public ClientAppRunner(User user) {
        this.user = user;
    }

    public void runClientApp() {
        System.out.println(user.getId() + " " + user.getName() + " " + user.getPassword() + " " + user.getRole());
    }
}
