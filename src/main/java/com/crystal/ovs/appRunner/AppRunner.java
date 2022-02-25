package com.crystal.ovs.appRunner;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class AppRunner {
    public static boolean isRunning = true;

    public static void runApp(){
        while(isRunning){
            OutputManager.printAppMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void login() {
        OutputManager.printMessage("Login");
        OutputManager.printLabel("Email");
        String email = InputManager.readStringField();
        OutputManager.printLabel("Password");
        String password = InputManager.readStringField();

        //Get the user based on email and password
        //this is just a placeholder user
        User user = new User(1, "asd", "asd", UserRole.ADMIN);

        if(user.getRole() == UserRole.CLIENT){
            ClientAppRunner clientAppRunner = new ClientAppRunner(user);
            clientAppRunner.runClientApp();

        } else {
            AdminAppRunner.runAdminApp();
        }
    }

    private static void createAccount() {
        OutputManager.printMessage("Create account");
        OutputManager.printLabel("Email");
        String email = InputManager.readStringField();
        OutputManager.printLabel("Password");
        String password = InputManager.readStringField();

        //create user with on CRUDUser
        OutputManager.printMessage("Account created");

    }
}
