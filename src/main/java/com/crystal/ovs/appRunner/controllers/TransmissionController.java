package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Transmission;
import com.crystal.ovs.dao.TransmissionType;
import com.crystal.ovs.database.crud.CrudTransmission;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

import java.util.ArrayList;
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
        OutputManager.printMessage("Create transmission:");
        try {
            Transmission transmission = readTransmission();
            CrudTransmission.insertTransmission(transmission);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Transmission readTransmission() throws ValidationException {
        OutputManager.printMessage("TransmissionType:");
        TransmissionType transmissionType = getEnumForTransmission();
        OutputManager.printMessage("Number of gears: ");
        int numberOfGears = InputManager.readIntegerField();

        Transmission transmission = new Transmission(1, transmissionType, numberOfGears);


        List<String> validationErrors = validateTransmission(transmission);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        else {
            return transmission;
        }
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
                        transmission.setNumberOfGears(userInput);
                        break;
                    }
                    case 0: {
                        break;
                    }
                }
            }
            List<String> validationErrors = validateTransmission(transmission);
            if(validationErrors.size() > 0){
                try{
                    throw new ValidationException(validationErrors);
                } catch (ValidationException e){
                    e.printStackTrace();
                }
            } else {
                CrudTransmission.updateTransmission(transmission);
            }
        } else {
            OutputManager.printMessage("Transmission not found!");
        }
    }

    private static TransmissionType getEnumForTransmission() {
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

    public static List<String> validateTransmission(Transmission transmission){
        List<String> validationErrors = new ArrayList<>();

        if(transmission.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(transmission.getNumberOfGears() < 3 || transmission.getNumberOfGears() > 15){
            validationErrors.add("A transmission can't have more than 15 gears and less than 3");
        }
        return validationErrors;
    }
}
