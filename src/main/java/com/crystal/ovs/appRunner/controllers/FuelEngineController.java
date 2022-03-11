package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.EngineLayout;
import com.crystal.ovs.dao.FuelEngine;
import com.crystal.ovs.dao.FuelType;
import com.crystal.ovs.dao.StrokeType;
import com.crystal.ovs.database.crud.CrudFuelEngine;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelEngineController {
    private static boolean isInFuelEngineController = true;

    public static void openFuelEngineController() {
        while (isInFuelEngineController) {
            OutputManager.printFuelEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command) {
                case 1:
                    getAllFuelEngines();
                    break;
                case 2:
                    getFuelEngineById();
                    break;
                case 3:
                    createFuelEngine();
                    break;
                case 4:
                    updateFuelEngine();
                    break;
                case 5:
                    deleteFuelEngine();
                    break;
                case 6:
                    isInFuelEngineController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllFuelEngines() {
        OutputManager.printMessage("Fuel engines");
        try {
            List<FuelEngine> fuelEngineList = CrudFuelEngine.selectAllFuelEngines();
            for (FuelEngine fuelEngine : fuelEngineList) {
                OutputManager.printMessage(fuelEngine.toString());
            }
        } catch (SQLException e) {
            OutputManager.printMessage(OutputTextType.ERROR, "There was a problem connecting to the database");
        }
    }

    private static void getFuelEngineById() {
        OutputManager.printMessage("Chose a fuel engines");
        OutputManager.printLabel("Enter fuel engine id");
        int id = InputManager.readIntegerField();

        try {
            FuelEngine fuelEngine = CrudFuelEngine.selectFuelEngineById(id);
            if (fuelEngine != null) {
                OutputManager.printMessage(fuelEngine.toString());
            } else {
                OutputManager.printMessage(OutputTextType.WARNING, "There is no fuel engine with that Id");
            }
        } catch (SQLException e) {
            OutputManager.printMessage(OutputTextType.ERROR,"There was a problem connecting to the database");
        }
    }

    private static void createFuelEngine() {
        try {
            FuelEngine fuelEngine = readFuelEngine();
            CrudFuelEngine.insertFuelEngine(fuelEngine);
        } catch (ValidationException e) {
            for (String error : e.getValidationErrors()) {
                OutputManager.printMessage(OutputTextType.ERROR, error);
            }
        }
    }

    private static void updateFuelEngine() {
        getAllFuelEngines();
        OutputManager.printMessage("Chose a fuel engines");
        OutputManager.printLabel("Enter fuel engine id");
        int id = InputManager.readIntegerField();

        try {
            FuelEngine fuelEngine = CrudFuelEngine.selectFuelEngineById(id);
            if (fuelEngine != null) {
                readUpdatedFields(fuelEngine);
                CrudFuelEngine.updateFuelEngine(fuelEngine);
            } else {
                OutputManager.printMessage(OutputTextType.WARNING, "There is no fuel engine with that Id");
            }
        } catch (SQLException e) {
            OutputManager.printMessage(OutputTextType.ERROR,"There was a problem connecting to the database");
        } catch (ValidationException e) {
            for (String error : e.getValidationErrors()) {
                OutputManager.printMessage(OutputTextType.ERROR, error);
            }
        }
    }

    private static void deleteFuelEngine() {
        OutputManager.printMessage("Chose a fuel engines");
        OutputManager.printLabel("Enter fuel engine id");
        int id = InputManager.readIntegerField();

        try {
            FuelEngine fuelEngine = CrudFuelEngine.selectFuelEngineById(id);
            if (fuelEngine != null) {
                OutputManager.printMessage(fuelEngine.toString());
                OutputManager.printMessage("Are you sure you want to delete this fuel engine? (y/n)");
                String response = InputManager.readStringField();

                if(response.equalsIgnoreCase("y")){
                    CrudFuelEngine.deleteFuelEngine(fuelEngine.getId());
                }
            } else {
                OutputManager.printMessage(OutputTextType.WARNING, "There is no fuel engine with that Id");
            }
        } catch (SQLException e) {
            OutputManager.printMessage(OutputTextType.ERROR, "There was a problem connecting to the database");
        }
    }

    //Helper methods
    private static int getFuelType() {
        OutputManager.printMessage("Fuel types: ");
        OutputManager.printFuelTypes();
        OutputManager.printLabel("Fuel type");
        return InputManager.readIntegerField() - 1;
    }

    private static int getEngineLayout() {
        OutputManager.printMessage("Engine layout: ");
        OutputManager.printEngineLayout();
        OutputManager.printLabel("Engine layout");
        return InputManager.readIntegerField() - 1;
    }

    private static int getStrokeType() {
        OutputManager.printMessage("Stroke type: ");
        OutputManager.printStrokeType();
        OutputManager.printLabel("Stroke type");
        return InputManager.readIntegerField() - 1;
    }

    private static FuelEngine readFuelEngine() throws ValidationException {
        int fuelType = getFuelType();
        OutputManager.printLabel("Fuel consumption");
        float fuelConsumption = InputManager.readFloatField();
        OutputManager.printLabel("Number of cylinders");
        int numberOfCylinders = InputManager.readIntegerField();
        OutputManager.printLabel("CO2 emissions");
        float co2Emissions = InputManager.readFloatField();
        int engineLayout = getEngineLayout();
        OutputManager.printLabel("Has turbine(y/n)");
        String hasTurbine = InputManager.readStringField();
        OutputManager.printLabel("Has super charge(y/n)");
        String hasSuperCharge = InputManager.readStringField();
        int strokeType = getStrokeType();
        OutputManager.printLabel("Displacement");
        float displacement = InputManager.readFloatField();

        List<String> validationErrors = validateFuelEngine(fuelType, fuelConsumption, numberOfCylinders, co2Emissions,
                engineLayout, hasTurbine, hasSuperCharge, strokeType, displacement);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        else {
            return new FuelEngine(-1, FuelType.values()[fuelType], fuelConsumption, numberOfCylinders,
                    co2Emissions, EngineLayout.values()[engineLayout], hasTurbine.equalsIgnoreCase("y"),
                    hasSuperCharge.equalsIgnoreCase("y"), StrokeType.values()[strokeType], displacement);
        }
    }

    private static void readUpdatedFields(FuelEngine fuelEngine) {
        boolean isUpdating = true;
        int option;
        while(isUpdating){
            OutputManager.printFuelEngineUpdateOptions();
            option = InputManager.readIntegerField();

            switch(option) {
                case 1: {
                    int fuelType = getFuelType();
                    if (fuelType < 0 || fuelType > FuelType.values().length) {
                        OutputManager.printMessage("Invalid fuel type");
                    } else {
                        fuelEngine.setFuelType(FuelType.values()[fuelType]);
                    }
                    break;
                }
                case 2: {
                    OutputManager.printLabel("Fuel consumption");
                    fuelEngine.setFuelConsumption(InputManager.readFloatField());
                    break;
                }
                case 3: {
                    OutputManager.printLabel("CO2 emissions");
                    fuelEngine.setCO2Emissions(InputManager.readFloatField());
                    break;
                }
                case 4: {
                    int engineLayout = getEngineLayout();
                    if (engineLayout < 0 || engineLayout > EngineLayout.values().length) {
                        OutputManager.printMessage("Invalid engine layout");
                    } else {
                        fuelEngine.setEngineLayout(EngineLayout.values()[engineLayout]);
                    }
                    break;
                }
                case 5: {
                    int strokeType = getStrokeType();
                    if (strokeType < 0 || strokeType > StrokeType.values().length) {
                        OutputManager.printMessage("Invalid stroke type");
                    } else {
                        fuelEngine.setStrokeType(StrokeType.values()[strokeType]);
                    }
                    break;
                }
                case 6:{
                    isUpdating = false;
                    break;
                }
            }
        }
    }

    private static List<String> validateFuelEngine(
            int fuelType, float fuelConsumption, int numberOfCylinders, float co2Emissions, int engineLayout,
            String hasTurbine, String hasSuperCharge, int strokeType, float displacement) {

        List<String> validationErrors = new ArrayList<>();
        if (fuelType < 0 || fuelType > FuelType.values().length) {
            validationErrors.add("Invalid fuel type");
        }
        if(fuelConsumption == Float.MIN_VALUE){
            validationErrors.add("Invalid fuel consumption");
        }
        if(numberOfCylinders == Integer.MIN_VALUE){
            validationErrors.add("Invalid number of cylinders");
        }
        if(co2Emissions == Float.MIN_VALUE){
            validationErrors.add("Invalid number of cylinders");
        }
        if (engineLayout < 0 || engineLayout > EngineLayout.values().length) {
            validationErrors.add("Invalid engine layout");
        }
        if(!hasTurbine.equalsIgnoreCase("y") && !hasTurbine.equalsIgnoreCase("n")){
            validationErrors.add("Invalid option for 'has turbine'");
        }
        if(!hasSuperCharge.equalsIgnoreCase("y") && !hasSuperCharge.equalsIgnoreCase("n")){
            validationErrors.add("Invalid option for 'has supercharge'");
        }
        if (strokeType < 0 || strokeType > StrokeType.values().length) {
            validationErrors.add("Invalid stroke type");
        }
        if(displacement == Float.MIN_VALUE){
            validationErrors.add("Invalid displacement");
        }
        return validationErrors;
    }
}