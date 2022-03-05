package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Transmission;
import com.crystal.ovs.dao.TransmissionType;
import com.crystal.ovs.database.crud.CrudTransmission;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import java.util.List;
import java.util.Objects;

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
        List<Transmission> transmissionList = CrudTransmission.selectAllTransmission();
        for(Transmission transmission : Objects.requireNonNull(transmissionList)) {
            OutputManager.printMessage(transmission.toString());
        }
    }

    private static void getTransmissionById() {
        OutputManager.printMessage("Insert transmission's id:");
        Transmission transmission = CrudTransmission.selectTransmissionById(InputManager.readIntegerField());
        if(transmission != null) {
            OutputManager.printMessage(transmission.toString());
        } else {
            OutputManager.printMessage("Transmission not found!");
        }
    }

    private static void createTransmission() {
    }

    private static void updateTransmission() {
        getAllTransmissions();
        OutputManager.printMessage("Choose a Transmission");
        OutputManager.printLabel("Enter Transmission id");
        Transmission transmission = CrudTransmission.selectTransmissionById(InputManager.readIntegerField());
        if(transmission != null) {
            int userOption = 1;
            while(userOption != 0) {
                OutputManager.printMessage("1. Update TransmissionType");
                OutputManager.printMessage("2. Update NumberOfGears");
                OutputManager.printMessage("0. Exit.");
                OutputManager.printMessage("Choose a field to update: ");
                userOption = InputManager.readIntegerField();
                switch(userOption) {
                    case 1: {
                        OutputManager.printMessage(" Give a Transmission type: ");
                        transmission.setType(getEnumForTransmission());
                        break;
                    }
                    case 2: {
                        OutputManager.printMessage(" Gears of transmission should be between 3 and 15");
                        int userInput = InputManager.readIntegerField();
                        if(userInput < 3 || userInput > 15) {
                            OutputManager.printMessage(" Number of gears is incorect");
                        } else {
                            transmission.setNumberOfGears(userInput);
                        }
                        break;
                    }
                    case 0: {
                        break;
                    }
                }
            }
            CrudTransmission.updateTransmission(transmission);
        } else {
            OutputManager.printMessage("Car not found!");
        }
    }

    private static TransmissionType getEnumForTransmission(){
        OutputManager.printMessage(" 1. Automatic");
        OutputManager.printMessage(" 2. Manual");
        OutputManager.printMessage(" 3. Dualclutch");
        int userOption = InputManager.readIntegerField();
        switch(userOption){
            case 1: {
                return TransmissionType.AUTOMATIC;
            }
            case 2: {
                return TransmissionType.MANUAL;
            }
            case 3: {
                return TransmissionType.DUALCLUTCH;
            }
        }
        return null;
    }

    private static void deleteTransmission() {
        OutputManager.printMessage("Insert Transmission's id that you want to delete:");
        CrudTransmission.deleteTransmission(InputManager.readIntegerField());
    }
}
