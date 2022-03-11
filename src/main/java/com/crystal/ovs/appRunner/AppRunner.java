package com.crystal.ovs.appRunner;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import com.crystal.ovs.database.crud.CrudUser;
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

        //will probably throw errors
        User user = CrudUser.selectUserByCredentials(email, password);

        if(user.getRole() == UserRole.CLIENT){
            ClientAppRunner clientAppRunner = new ClientAppRunner(user);
            clientAppRunner.runClientApp();

        } else {
            AdminAppRunner.runAdminApp();
        }
    }

    private static void createAccount() {
        OutputManager.printMessage("Create account");
        OutputManager.printLabel("Name");
        String name = InputManager.readStringField();
        OutputManager.printLabel("Email");
        String email = InputManager.readStringField();
        OutputManager.printLabel("Password");
        String password = InputManager.readStringField();

        User user = new User(0, name, password, UserRole.CLIENT, email);

        //will probably throw errors
        CrudUser.insertUser(user);
        OutputManager.printMessage("Account created");

    }
}
