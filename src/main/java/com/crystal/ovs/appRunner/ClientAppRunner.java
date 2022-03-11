package com.crystal.ovs.appRunner;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class ClientAppRunner {
    private User user;

    private static boolean isInClientController = true;

    public ClientAppRunner(User user) {
        this.user = user;
    }

    public void runClientApp() {
        while (isInClientController) {
            OutputManager.printFuelEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command) {
                case 1:
                    getPosts();
                    break;
                case 2:
                    getPostById();
                    break;
                case 3:
                    buyCar();
                    break;
                case 4:
                    transactionHistory();
                    break;
                case 5:
                    isInClientController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private void getPosts() {
    }

    private void getPostById() {
    }

    private void buyCar() {
    }

    private void transactionHistory() {
    }
}
