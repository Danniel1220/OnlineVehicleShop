package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class TransmissionController {
    private static boolean isInTransmissionController = true;

    public static void openTransmissionController(){
        while(isInTransmissionController){
            OutputManager.printTransmissionControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    getAllTransmissions();
                    break;
                case 2:
                    getTransmissionById();
                    break;
                case 3:
                    createTransmission();
                    break;
                case 4:
                    updateTransmission();
                    break;
                case 5:
                    deleteTransmission();
                    break;
                case 6:
                    isInTransmissionController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllTransmissions() {
    }

    private static void getTransmissionById() {
    }

    private static void createTransmission() {
    }

    private static void updateTransmission() {
    }

    private static void deleteTransmission() {
    }
}
