package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Engine;
import com.crystal.ovs.database.crud.CrudEngine;
import com.crystal.ovs.exceptions.*;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EngineController {
    private static boolean isInEngineController = true;

    public static void openEngineController(){
        while(isInEngineController){
            OutputManager.printEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    getAllEngines();
                    break;
                case 2:
                    getEngineById();
                    break;
                case 3:
                    createEngine();
                    break;
                case 4:
                    updateEngine();
                    break;
                case 5:
                    deleteEngine();
                    break;
                case 6:
                    isInEngineController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllEngines() {
        List<Engine> engines = CrudEngine.selectAllEngine();
        for(Engine engine : Objects.requireNonNull(engines)) {
            OutputManager.printMessage(engine.toString());
        }
    }

    private static void getEngineById() {
        OutputManager.printMessage("Insert engine's id:");
        Engine engine= CrudEngine.selectEngineById(InputManager.readIntegerField());
        if(engine != null) {
            OutputManager.printMessage(engine.toString());
        } else {
            OutputManager.printMessage("Engine not found!");
        }
    }

    private static void createEngine() {
        OutputManager.printMessage("Create a new  engine:");
        try {
            Engine newEngine = readEngine();
            CrudEngine.insertEngine(newEngine);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
    private static Engine readEngine() throws ValidationException {
        OutputManager.printMessage("Horse power");
        int horsepower = InputManager.readIntegerField();
        OutputManager.printMessage("torque: ");
        int torque = InputManager.readIntegerField();
        OutputManager.printMessage("fuel engine id: ");
        int fuelEngineId = InputManager.readIntegerField();
        OutputManager.printMessage("electric engine id: ");
        int electricEngineId = InputManager.readIntegerField();


        Engine engine = new Engine(1, horsepower, torque,fuelEngineId,electricEngineId);


        List<String> validationErrors = validateEngine(engine);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        else {
            return engine;
        }
    }


    private static void updateEngine() {
        getAllEngines();
        OutputManager.printMessage("Choose an  engine:");
        OutputManager.printLabel("Enter engine's id: ");
        Engine Engine = CrudEngine.selectEngineById(InputManager.readIntegerField());
        if(Engine != null) {
            int userOption = 1;
            while(userOption != 0) {
                OutputManager.printMessage("1. Update engine's horse power.");
                OutputManager.printMessage("2. Update  engine's torque.");
                OutputManager.printMessage("3. Update engine's fuel id.");
                OutputManager.printMessage("4. Update engine's electric id.");
                OutputManager.printMessage("0. Exit.");
                OutputManager.printMessage("Choose a field to update: ");
                userOption = InputManager.readIntegerField();
                switch(userOption) {
                    case 1: {
                        OutputManager.printMessage("Give engine's horse power: ");
                        Engine.setHorsePower(InputManager.readIntegerField());
                        break;
                    }
                    case 2: {
                        OutputManager.printMessage("Give engine's  torque: ");
                        Engine.setTorque(InputManager.readIntegerField());
                        break;
                    }
                    case 3: {
                        OutputManager.printMessage("Give  engine's fuel id: ");
                        Engine.setFuelEngine(InputManager.readIntegerField());
                        break;
                    }
                    case 4: {
                        OutputManager.printMessage("Give  engine's electric id: ");
                        Engine.setElectricEngine(InputManager.readIntegerField());
                        break;
                    }
                    case 0: {
                        break;
                    }
                }
            }
            List<String> validationErrors = validateEngine(Engine);
            if(validateEngine(Engine).size() > 0) {
                try {
                    throw new ValidationException(validationErrors);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            } else {
                CrudEngine.updateAllEngineById(Engine);
            }
        } else {
            OutputManager.printMessage("Engine not found!");
        }
    }

    private static void deleteEngine() {
        OutputManager.printMessage("Insert engine's id that you want to delete:");
        CrudEngine.deleteEngine(InputManager.readIntegerField());
    }
    public static List<String> validateEngine(Engine engine){
        List<String> validationErrors = new ArrayList<>();

        if(engine.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(engine.getHorsePower() <= 0) {
            validationErrors.add("Horse power cannot be less than or equal to 60");
        }
        if(engine.getTorque() <= 0) {
            validationErrors.add("Torque cannot be less than or equal to 0");
        }
        if(engine.getElectricEngine() <= 1) {
            validationErrors.add("Electric engine id cannot be less than or equal to 1");
        }
        if(engine.getFuelEngine() <= 0) {
            validationErrors.add("Fuel engine id cannot be less than or equal to 1");
        }

        return validationErrors;
    }
}
