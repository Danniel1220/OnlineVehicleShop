package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class CarController {
    private static boolean isInCarController = true;

    public static void openCarController(){
        while(isInCarController){
            OutputManager.printCarControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    getAllCars();
                    break;
                case 2:
                    getCarById();
                    break;
                case 3:
                    createCar();
                    break;
                case 4:
                    updateCar();
                    break;
                case 5:
                    deleteCar();
                    break;
                case 6:
                    isInCarController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllCars() {
    }

    private static void getCarById() {
    }

    private static void createCar() {
    }

    private static void updateCar() {
    }

    private static void deleteCar() {
    }
}
