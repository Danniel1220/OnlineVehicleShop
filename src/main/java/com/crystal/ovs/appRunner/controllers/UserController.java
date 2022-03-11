package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class UserController {
    private static boolean isInUserController = true;

    public static void openUserController() {
        while (isInUserController) {
            OutputManager.printFuelEngineControllerMenu();
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
                case 5:
                    getUserId();
                    break;
                case 6:
                    createUser();
                    break;
                case 7:
                    updateUser();
                    break;
                case 8:
                    deleteUser();
                    break;
                case 9:
                    isInUserController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllUsers() {
    }

    private static void getAllAdminUsers() {
    }

    private static void getAllClientUsers() {
    }

    private static void getUserId() {
    }

    private static void createUser() {
    }

    private static void updateUser() {
    }

    private static void deleteUser() {
    }
}
