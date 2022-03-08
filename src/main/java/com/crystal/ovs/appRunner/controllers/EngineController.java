package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Engine;
import com.crystal.ovs.database.crud.CrudEngine;
import com.crystal.ovs.exceptions.ValidationException;
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
        OutputManager.printMessage("All engines");

        List<Engine> engineList = CrudEngine.selectAllEngine();
        for(Engine engine : Objects.requireNonNull(engineList)) {
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
            Engine engine = readEngine();
            if(engine != null) {
                CrudEngine.insertEngine(engine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void updateEngine() {
        getAllEngines();
        OutputManager.printMessage("Choose an  engine:");
        OutputManager.printLabel("Enter engine's id: ");
        Engine engine = CrudEngine.selectEngineById(InputManager.readIntegerField());
        if(engine != null) {
            int userOption = 1;
            while(userOption != 0) {
                OutputManager.printMessage("1. Update engine's fuel id.");
                OutputManager.printMessage("2. Update engine's electric id.");
                OutputManager.printMessage("3. Update engine's horse power.");
                OutputManager.printMessage("4. Update  engine's torque.");
                OutputManager.printMessage("5. Exit.");
                OutputManager.printMessage("Choose a field to update: ");
                userOption = InputManager.readIntegerField();
                switch(userOption) {
                    case 1: {
                        OutputManager.printMessage("Give  engine's fuel id: ");
                        engine.setFuelEngineId(InputManager.readIntegerField());
                        break;
                    }
                    case 2: {
                        OutputManager.printMessage("Give  engine's electric id: ");
                        engine.setElectricEngineId(InputManager.readIntegerField());
                        break;
                    }
                    case 3: {
                        OutputManager.printMessage("Give engine's horse power: ");
                        engine.setHorsePower(InputManager.readIntegerField());
                        break;
                    }
                    case 4: {
                        OutputManager.printMessage("Give engine's  torque: ");
                        engine.setTorque(InputManager.readIntegerField());
                        break;
                    }


                    case 5: {
                        break;
                    }
                }
            }
            List<String> validationErrors = validateEngine(engine);
            if(validateEngine(engine).size() > 0) {
                try {
                    throw new ValidationException(validationErrors);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            } else {
                CrudEngine.updateAllEngineById(engine);
            }
        } else {
            OutputManager.printMessage("Engine not found!");
        }
    }

    private static void deleteEngine() {
        OutputManager.printMessage("Insert engine's id that you want to delete:");
        CrudEngine.deleteEngine(InputManager.readIntegerField());
    }

    private static Engine readEngine() throws ValidationException {
        OutputManager.printMessage("fuel engine id: ");
        int fuelEngineId = InputManager.readIntegerField();
        OutputManager.printMessage("electric engine id: ");
        int electricEngineId = InputManager.readIntegerField();
        OutputManager.printMessage("Horse power");
        int horsePower = InputManager.readIntegerField();
        OutputManager.printMessage("torque: ");
        int torque = InputManager.readIntegerField();







        Engine engine = new Engine(1, fuelEngineId, electricEngineId, horsePower, torque);

        List<String> validationErrors = validateEngine(engine);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        else {
            return engine;
        }
    }


    public static List<String> validateEngine(Engine engine){
        List<String> validationErrors = new ArrayList<>();

        if(engine.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(engine.getFuelEngineId() <= 0) {
            validationErrors.add("Fuel engine id cannot be less than or equal to 0");
        }
        if(engine.getElectricEngineId() <= 0) {
            validationErrors.add("Electric engine id cannot be less than or equal to 0");
        }
        if(engine.getHorsePower() <= 0) {
            validationErrors.add("Horse power cannot be less than or equal to 60");
        }
        if(engine.getTorque() <= 0) {
            validationErrors.add("Torque cannot be less than or equal to 0");
        }



        return validationErrors;
    }

}
