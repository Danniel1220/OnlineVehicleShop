package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

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
    }

    private static void getFuelEngineById() {
    }

    private static void createFuelEngine() {
    }

    private static void updateFuelEngine() {
    }

    private static void deleteCar() {
    }
}
