package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.CarType;
import com.crystal.ovs.dao.TractionType;
import com.crystal.ovs.database.crud.CrudCar;
import com.crystal.ovs.database.crud.CrudEngine;
import com.crystal.ovs.database.crud.CrudTransmission;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        OutputManager.printMessage("Insert a new car");
        try {
            Car car = readCar();
            if(car != null) {
                CrudCar.insertCar(car);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Car readCar() throws ValidationException {
        OutputManager.printMessage("Car Brand:");
        String carBrand = InputManager.readStringField();
        OutputManager.printMessage("Car Model:");
        String carModel = InputManager.readStringField();
        OutputManager.printMessage("Car VIN");
        String carVIN = InputManager.readStringField();
        OutputManager.printMessage("Car manufacturing year:");
        int manufacturingYear = InputManager.readIntegerField();
        OutputManager.printMessage("Chose an engine ID: ");
        OutputManager.printMessage(CrudEngine.selectAllEngine().toString());
        int engineId = InputManager.readIntegerField();
        OutputManager.printMessage("Chose a transmission");
        OutputManager.printMessage(CrudTransmission.selectAllTransmission().toString());
        int transmissionId = InputManager.readIntegerField();
        OutputManager.printMessage("Write a CarType (SEDAN, COUPE, SPORTS, WAGON, HATCHBACK, CONVERTIBLE, SUV, MINIVAN, VAN, PICKUPTRUCK)");
        CarType carType = CarType.valueOf(InputManager.readStringField().toUpperCase(Locale.ROOT));
        OutputManager.printMessage("Write a TractionType (AWD, FWD, RWD)");
        TractionType tractionType = TractionType.valueOf(InputManager.readStringField().toUpperCase(Locale.ROOT));
        OutputManager.printMessage("Write number of doors min = 2 max = 7");
        int numberOfDoors = InputManager.readIntegerField();
        OutputManager.printMessage("Write a color for the car:");
        String color = InputManager.readStringField().toUpperCase();

        Car car = Car.builder().id(1)
                .brand(carBrand).model(carModel).VIN(carVIN)
                .manufacturingYear(manufacturingYear).carType(carType)
                .engineId(engineId).transmissionId(transmissionId)
                .tractionType(tractionType).numberOfDoors(numberOfDoors)
                .color(color)
                .build();

        if (!hasErrors(validateCar(car))) {
           return car;
        }
        return null;
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
                        OutputManager.printMessage("Choose engine id:");
                        OutputManager.printMessage(Objects.requireNonNull(CrudEngine.selectAllEngine()).toString());
                        int engineId = car.getEngineId();
                        car.setEngineId(InputManager.readIntegerField());
                        if(hasErrors(validateCar(car))) {
                            car.setEngineId(engineId);
                        }
                        break;
                    }
                    case 2: {
                        OutputManager.printMessage("Choose transmission id:");
                        OutputManager.printMessage(Objects.requireNonNull(CrudTransmission.selectAllTransmission()).toString());
                        int transmissionId = car.getTransmissionId();
                        car.setTransmissionId(InputManager.readIntegerField());
                        if(hasErrors(validateCar(car))) {
                            car.setTransmissionId(transmissionId);
                        }
                        break;
                    }
                    case 3: {
                        OutputManager.printMessage("Insert color:");
                        String color = car.getColor();
                        car.setColor(InputManager.readStringField().toUpperCase());
                        if(hasErrors(validateCar(car))) {
                            car.setColor(color);
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

    public static List<String> validateCar (Car car) {
        List<String> validationErrors = new ArrayList<>();

        if (car.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if (car.getBrand().isEmpty()) {
            validationErrors.add("Brand can't be empty");
        }
        if (car.getModel().isEmpty()) {
            validationErrors.add("Model can't be empty");
        }
        if (car.getVIN().isEmpty()) {
            validationErrors.add("VIN number can't be empty");
        }
        if (car.getManufacturingYear() < 2019) {
            validationErrors.add("This model of car is already out of manufacturing time");
        }
        if (car.getManufacturingYear() > 2023) {
            validationErrors.add("This model of car is not out yet");
        }
        if (car.getEngineId() <= 0) {
            validationErrors.add("EngineId can't be less than or equal to 0");
        }
        if (car.getTransmissionId() <= 0) {
            validationErrors.add("TransmissionId can't be less than or equal to 0");
        }
        if (car.getNumberOfDoors() < 1 || car.getNumberOfDoors() > 6) {
            validationErrors.add("A car can have a min number of doors of 1 and a maximum of 6");
        }
        if(car.getColor().isBlank()) {
            validationErrors.add("You have to enter a color!");
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
