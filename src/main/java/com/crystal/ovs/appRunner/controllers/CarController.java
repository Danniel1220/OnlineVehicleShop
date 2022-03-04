package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.database.crud.CrudCar;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import java.awt.*;
import java.util.List;
import java.util.Objects;

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
        List<Car> cars = CrudCar.selectAllCars();
        for(Car car : Objects.requireNonNull(cars)) {
            OutputManager.printMessage(car.toString());
        }
    }

    private static void getCarById() {
        OutputManager.printMessage("Insert car's id:");
        Car car = CrudCar.selectCarById(InputManager.readIntegerField());
        if(car != null) {
            OutputManager.printMessage(car.toString());
        } else {
            OutputManager.printMessage("Car not found!");
        }
    }

    private static void createCar() {
        // we will use CrudOperations for retrieving the transmission and engine form the db
    }

    private static void updateCar() { // to be tested
        getAllCars();
        OutputManager.printMessage("Choose a car");
        OutputManager.printLabel("Enter car id");
        Car car = CrudCar.selectCarById(InputManager.readIntegerField());
        if(car != null) {
            int userOption = 1;
            while(userOption != 0) {
                OutputManager.printMessage("1. Update engine id.");
                OutputManager.printMessage("2. Update transmission id.");
                OutputManager.printMessage("3. Update color.");
                OutputManager.printMessage("0. Exit.");
                OutputManager.printMessage("Choose a field to update: ");
                userOption = InputManager.readIntegerField();
                switch(userOption) {
                    case 1: {
                        car.setEngineId(InputManager.readIntegerField());
                        break;
                    }
                    case 2: {
                        car.setTransmissionId(InputManager.readIntegerField());
                        break;
                    }
                    case 3: {
                        Color color = Color.getColor(InputManager.readStringField());
                        if(color != null) {
                            car.setColor(color);
                        } else {
                            OutputManager.printMessage("Invalid color!");
                        }
                        break;
                    }
                }
            }
            CrudCar.updateCar(car);
        } else {
            OutputManager.printMessage("Car not found!");
        }
    }

    private static void deleteCar() {
        OutputManager.printMessage("Insert car's id:");
        CrudCar.deleteCar(InputManager.readIntegerField());
    }
}
