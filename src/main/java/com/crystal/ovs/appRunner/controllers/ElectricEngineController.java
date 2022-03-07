package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.ElectricEngine;
import com.crystal.ovs.database.crud.CrudElectricEngine;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElectricEngineController {
    private static boolean isInElectricEngineController = true;

    public static void openElectricEngineController(){
        while(isInElectricEngineController){
            OutputManager.printElectricEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    getAllElectricEngines();
                    break;
                case 2:
                    getElectricEnginesById();
                    break;
                case 3:
                    createElectricEngine();
                    break;
                case 4:
                    updateElectricEngine();
                    break;
                case 5:
                    deleteElectricEngine();
                    break;
                case 6:
                    isInElectricEngineController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllElectricEngines() {
        List<ElectricEngine> electricEngines = CrudElectricEngine.selectAllElectricEngines();
        for(ElectricEngine electricEngine : Objects.requireNonNull(electricEngines)) {
            OutputManager.printMessage(electricEngine.toString());
        }
    }

    private static void getElectricEnginesById() {
        OutputManager.printMessage("Insert electric engine's id:");
        ElectricEngine electricEngine = CrudElectricEngine.selectElectricEngineById(InputManager.readIntegerField());
        if(electricEngine != null) {
            OutputManager.printMessage(electricEngine.toString());
        } else {
            OutputManager.printMessage("Electric engine not found!");
        }
    }

    private static void createElectricEngine() {
        OutputManager.printMessage("Let's create a new electric engine:");
        try {
            ElectricEngine newElectricEngine = readElectricEngine();
            if(newElectricEngine != null) {
                CrudElectricEngine.insertElectricEngine(newElectricEngine);
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private static void updateElectricEngine() {
        getAllElectricEngines();
        OutputManager.printMessage("Choose an electric engine:");
        OutputManager.printLabel("Enter electric engine's id: ");
        ElectricEngine electricEngine = CrudElectricEngine.selectElectricEngineById(InputManager.readIntegerField());
        if(electricEngine != null) {
            int userOption = 1;
            while(userOption != 0) {
                OutputManager.printMessage("1. Update electric engine's type.");
                OutputManager.printMessage("2. Update electric engine's battery capacity.");
                OutputManager.printMessage("3. Update electric engine's range.");
                OutputManager.printMessage("0. Exit.");
                OutputManager.printMessage("Choose a field to update: ");
                userOption = InputManager.readIntegerField();
                switch(userOption) {
                    case 1: {
                        OutputManager.printMessage("Give electric engine's type: ");
                        String engineType = electricEngine.getType();
                        electricEngine.setType(InputManager.readStringField());
                        if(hasErrors(validateElectricEngine(electricEngine))) {
                            electricEngine.setType(engineType);
                        }
                        break;
                    }
                    case 2: {
                        OutputManager.printMessage("Give electric engine's battery capacity: ");
                        int electricEngineBatteryCapacity = electricEngine.getBatteryCapacity();
                        electricEngine.setBatteryCapacity(InputManager.readIntegerField());
                        if(hasErrors(validateElectricEngine(electricEngine))) {
                            electricEngine.setBatteryCapacity(electricEngineBatteryCapacity);
                        }
                        break;
                    }
                    case 3: {
                        OutputManager.printMessage("Give electric engine's range: ");
                        int electricEngineRange = electricEngine.getRange();
                        electricEngine.setRange(InputManager.readIntegerField());
                        if(hasErrors(validateElectricEngine(electricEngine))) {
                            electricEngine.setRange(electricEngineRange);
                        }
                        break;
                    }
                    case 0: {
                        break;
                    }
                }
            }
            CrudElectricEngine.updateAllById(electricEngine);
        } else {
            OutputManager.printMessage("Electric engine not found!");
        }
    }

    private static void deleteElectricEngine() {
        OutputManager.printMessage("Insert electric engine's id:");
        CrudElectricEngine.deleteById(InputManager.readIntegerField());
    }

    private static ElectricEngine readElectricEngine() throws ValidationException{
        OutputManager.printLabel("Electric engine's type: ");
        String electricEngineType = InputManager.readStringField();
        OutputManager.printLabel("Electric engine's battery capacity: ");
        Integer electricEngineBatteryCapacity = InputManager.readIntegerField();
        OutputManager.printLabel("Electric engine's range: ");
        Integer electricEngineRange = InputManager.readIntegerField();

        ElectricEngine newElectricEngine = new ElectricEngine(-1, electricEngineType, electricEngineBatteryCapacity, electricEngineRange);

        if (!hasErrors(validateElectricEngine(newElectricEngine))) {
            return newElectricEngine;
        }
        return null;
    }

    private static List<String> validateElectricEngine(ElectricEngine electricEngine) {
        List<String> validationErrors = new ArrayList<>();

        if (electricEngine.getType().equals("")) {
            validationErrors.add("Type is empty!");
        }
        if (electricEngine.getBatteryCapacity() < 17) {
            validationErrors.add("Battery capacity is too low for an electric vehicle!");
        }
        if (electricEngine.getRange() < 135) {
            validationErrors.add("Range is too small for a car's electric engine!");
        }

        return validationErrors;
    }

    private static boolean hasErrors(List<String> validationErrors) {
        if (validationErrors.size() > 0) {
            for (String error : validationErrors) {
                OutputManager.printMessage(error);
            }
            return true;
        }
        return false;
    }
}
