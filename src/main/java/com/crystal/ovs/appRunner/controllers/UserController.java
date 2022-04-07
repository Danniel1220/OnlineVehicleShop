package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import com.crystal.ovs.database.crud.CrudUser;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserController {
    private static boolean isInUserController = true;

    public static void openUserController() {
        while (isInUserController) {
            OutputManager.printUserControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command) {
                case 1:
                    getAllUsers();
                    break;
                case 2:
                    getAllAdminUsers();
                    break;
                case 3:
                    getAllClientUsers();
                    break;
                case 4:
                    getUserId();
                    break;
                case 5:
                    createUser();
                    break;
                case 6:
                    updateUser();
                    break;
                case 7:
                    deleteUser();
                    break;
                case 8:
                    isInUserController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllUsers() {
        List<User> users = CrudUser.selectAllUsers();
        for(User user : Objects.requireNonNull(users)) {
            OutputManager.printMessage(user.toString());
        }
    }

    private static void getAllAdminUsers() {
        List<User> users = CrudUser.selectAllAdminUsers();
        for(User user : Objects.requireNonNull(users)) {
            OutputManager.printMessage(user.toString());
        }
    }

    private static void getAllClientUsers() {
        List<User> users = CrudUser.selectAllClientUsers();
        for(User user : Objects.requireNonNull(users)) {
            OutputManager.printMessage(user.toString());
        }
    }

    private static void getUserId() {
        OutputManager.printMessage("Insert user's id:");
        User user = CrudUser.selectUserById(InputManager.readIntegerField());
        if(user != null) {
            OutputManager.printMessage(user.toString());
        } else {
            OutputManager.printMessage(OutputTextType.WARNING,"WARNING: User not found!");
        }
    }

    private static void createUser() {
        OutputManager.printMessage("Create account:");
        OutputManager.printLabel("Name");
        String name = InputManager.readStringField();
        OutputManager.printLabel("Email");
        String email = InputManager.readStringField();
        OutputManager.printLabel("Password");
        String password = InputManager.readStringField();

        try {
            User user = new User(0, name, password, UserRole.CLIENT, email);
            List<String> validationErrors = validateAccountInformationForRegistration(user);
            if(!hasErrors(validationErrors)) {
                CrudUser.insertUser(user);
                OutputManager.printMessage(OutputTextType.CONNECTION, "Account created!");
            }
        } catch (Exception e) {
            OutputManager.printMessage(OutputTextType.ERROR, "There was a problem creating your account!");
            e.printStackTrace();
        }
    }

    private static void updateUser() {
        getAllUsers();
        OutputManager.printMessage("Choose an user:");
        OutputManager.printLabel("Enter user's id: ");
        User user = CrudUser.selectUserById(InputManager.readIntegerField());
        if(user != null) {
            int userOption = 1;
            while(userOption != 0) {
                OutputManager.printMessage("1. Update name");
                OutputManager.printMessage("2. Update password.");
                OutputManager.printMessage("3. Update email.");
                OutputManager.printMessage("0. Exit.");
                OutputManager.printMessage("Choose a field to update: ");
                userOption = InputManager.readIntegerField();
                switch(userOption) {
                    case 1: {
                        OutputManager.printMessage("Give  user name ");
                        user.setName(InputManager.readStringField());
                        break;

                    }
                    case 2: {
                        OutputManager.printMessage("Give  new password ");
                        user.setPassword(InputManager.readStringField());
                        break;
                    }
                    case 3: {
                        OutputManager.printMessage("Give  new email ");
                        user.setEmail(InputManager.readStringField());
                        break;
                    }
                    case 0: {
                        break;
                    }
                }
            }
            CrudUser.updateUser(user);
        } else {
            OutputManager.printMessage(OutputTextType.WARNING,"WARNING: User was not found!");
        }
    }

    private static void deleteUser() {
        OutputManager.printMessage("Insert user's id:");
        CrudUser.deleteUser(InputManager.readIntegerField());
    }


    private static boolean hasErrors(List<String> validationErrors) {
        if (validationErrors.size() > 0) {
            for (String error : validationErrors) {
                OutputManager.printMessage(OutputTextType.ERROR,error);
            }
            return true;
        }
        return false;
    }
    private static List<String> validateAccountInformationForRegistration(User user) {
        List<String> validationErrors = new ArrayList<>();

        if(user.getEmail().isEmpty() || user.getEmail().isBlank() || user.getEmail() == null) {
            validationErrors.add("ERROR: You must enter an email!");
        }
        if(user.getName().isEmpty() || user.getName().isBlank() || user.getName() == null) {
            validationErrors.add("ERROR: You must enter an username!");
        }
        if(user.getPassword().isEmpty() || user.getPassword().isBlank() || user.getPassword() == null) {
            validationErrors.add("ERROR: You must enter a password!");
        }

        List<User> existingUsers = CrudUser.selectAllUsers();
        for(User existingUser : Objects.requireNonNull(existingUsers)) {
            if(existingUser.getEmail().equals(user.getEmail())) {
                validationErrors.add("ERROR: Email already taken!");
                break;
            }
            if(existingUser.getName().equals(user.getName())) {
                validationErrors.add("ERROR: Username already taken!");
                break;
            }
        }
        return validationErrors;
    }
}
