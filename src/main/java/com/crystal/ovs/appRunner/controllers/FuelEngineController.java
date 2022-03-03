package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.FuelEngine;
import com.crystal.ovs.dao.FuelType;
import com.crystal.ovs.database.crud.CrudFuelEngine;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelEngineController {
    private static boolean isInFuelEngineController = true;

    public static void openFuelEngineController(){
        while(isInFuelEngineController){
            OutputManager.printFuelEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
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
                    deleteCar();
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
            OutputManager.printTableHead();
            for (FuelEngine fuelEngine: fuelEngineList) {
                OutputManager.printFuelEngine(fuelEngine);
            }
        } catch (SQLException e) {
            OutputManager.printMessage("There was a problem connecting to the database");
        }
    }

    private static void getFuelEngineById() {
        OutputManager.printMessage("Chose a fuel engines");
        OutputManager.printLabel("Enter fuel engine id");
        int id = InputManager.readIntegerField();

        try {
            FuelEngine fuelEngine = CrudFuelEngine.selectFuelEngineById(id);
            if(fuelEngine != null){
                OutputManager.printFuelEngine(fuelEngine);
            } else {
                OutputManager.printMessage("There is no fuel engine with that Id");
            }
        } catch (SQLException e) {
            OutputManager.printMessage("There was a problem connecting to the database");
        }
    }

    private static void createFuelEngine() {

    }

    private static void updateFuelEngine() {
    }

    private static void deleteCar() {
    }

    private static FuelEngine readFuelEngine(){
        OutputManager.printMessage("Create fuel engine");
        OutputManager.printMessage("Fuel types: ");
        OutputManager.printFuelTypes();
        OutputManager.printLabel("Fuel type");
        int fuelType = InputManager.readIntegerField() - 1;

        OutputManager.printLabel("Fuel consumption");
        float fuelConsumption = InputManager.readFloatField();

        OutputManager.printLabel("Number of cylinders");
        int numberOfCylinders = InputManager.readIntegerField();

        OutputManager.printLabel("CO2 emissions");
        float co2Emissions = InputManager.readFloatField();

        OutputManager.printMessage("Engine layout: ");
        OutputManager.printEngineLayout();
        OutputManager.printLabel("Engine layout");
        int engineLayout = InputManager.readIntegerField() - 1;

        OutputManager.printLabel("Has turbine(y/n)");
        String hasTurbine = InputManager.readStringField();

        OutputManager.printLabel("Has super charge(y/n)");
        String hasSuperCharge = InputManager.readStringField();

        OutputManager.printMessage("Stroke type: ");
        OutputManager.printStrokeType();
        OutputManager.printLabel("Stroke type");
        int strokeType = InputManager.readIntegerField() - 1;

        OutputManager.printLabel("Displacement");
        float displacement = InputManager.readFloatField();

        FuelEngine fuelEngine = null;
        List<String> validationErrors = validateFuelEngine(fuelType, fuelConsumption, numberOfCylinders, co2Emissions,
                engineLayout, hasTurbine, hasSuperCharge, strokeType, displacement);
        return fuelEngine;
    }

    private static List<String> validateFuelEngine(
            int fuelType, float fuelConsumption, int numberOfCylinders, float co2Emissions, int engineLayout,
            String hasTurbine, String hasSuperCharge, int strokeType, float displacement) {

        List<String> validationErrors = new ArrayList<>();
        if(fuelType < 0 || fuelType > FuelType.values().length){
            validationErrors.add("Invalid fuel type");
        }
        return validationErrors;
    }
}
