package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.CarType;
import com.crystal.ovs.dao.TractionType;
import com.crystal.ovs.dao.TransmissionType;
import com.crystal.ovs.database.crud.CrudCar;
import com.crystal.ovs.database.crud.CrudEngine;
import com.crystal.ovs.database.crud.CrudTransmission;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import java.awt.*;
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
        // we will use CrudOperations for retrieving the transmission and engine form the db
        //TODO CrudEngine was not refactored to return a list of cars and a car object on selectAll and selectCarById
        //TODO it would be great if you would do it by next week thx :)) x10 -Marius
        OutputManager.printMessage("Insert a new car");
        try {
            Car car = readCar();
            CrudCar.insertCar(car);
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
        OutputManager.printMessage("Write a TractionType ()");
        TractionType tractionType = TractionType.valueOf(InputManager.readStringField().toUpperCase(Locale.ROOT));
        OutputManager.printMessage("Write number of doors min = 2 max = 7");
        int numberOfDoors = InputManager.readIntegerField();
        OutputManager.printMessage("Write a color for the car:");
        Color color = Color.getColor(InputManager.readStringField());

        Car car = Car.builder().id(1)
                .brand(carBrand).model(carModel).VIN(carVIN)
                .manufacturingYear(manufacturingYear).carType(carType)
                .engineId(engineId).transmissionId(transmissionId)
                .tractionType(tractionType).numberOfDoors(numberOfDoors).build();
        List<String> validationErrors = validateCar(car);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        } else {
            return car;
        }
    }

    private static CarType getCarType(){
        OutputManager.printMessage(" 1. SEDAN,\n" +
                "    2. COUPE,\n" +
                "    3. SPORTS,\n" +
                "    4. WAGON,\n" +
                "    5. HATCHBACK,\n" +
                "    6. CONVERTIBLE,\n" +
                "    7. SUV,\n" +
                "    8. MINIVAN,\n" +
                "    9. VAN,\n" +
                "    10. PICKUPTRUCK");
        int userOption = InputManager.readIntegerField();
        switch(userOption){
            case 1: {
                return CarType.SEDAN;
            }
            case 2: {
                return CarType.COUPE;
            }
            case 3: {
                return CarType.SPORTS;
            }
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
        /*if(!colorCodeValidation(car.getColor().toString())){
            validationErrors.add("The color introduced is not valid");
        }*/
        return validationErrors;
    }
}
