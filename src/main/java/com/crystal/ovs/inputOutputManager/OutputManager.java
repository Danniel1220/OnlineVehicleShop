package com.crystal.ovs.inputOutputManager;

import com.crystal.ovs.dao.*;

import java.util.List;

public class OutputManager {
    public static void printAppMenu() {
        System.out.print("Menu\n");
        System.out.print("1. Login\n");
        System.out.print("2. Create account\n");
        System.out.print("3. Exit\n");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void invalidCommandMessage() {
        System.out.print("Invalid command. Please try again\n");
    }

    public static void printMessage(String message) {
        System.out.printf("%s\n", message);
    }

    public static void printLabel(String message) {
        System.out.printf("%s: ", message);
    }

    public static void printAdminAppMenu() {
        System.out.print("Menu\n");
        System.out.print("1. Car\n");
        System.out.print("2. Engine\n");
        System.out.print("3. Fuel engine\n");
        System.out.print("4. Electric engine\n");
        System.out.print("5. Transmission\n");
        System.out.print("6. Post\n");
        System.out.print("7. Exit\n");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printCarControllerMenu() {
        System.out.print("Car menu\n");
        System.out.print("1. Get all cars\n");
        System.out.print("2. Get car by id\n");
        System.out.print("3. Create car\n");
        System.out.print("4. Update car\n");
        System.out.print("5. Delete car\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printEngineControllerMenu() {
        System.out.print("Engine menu\n");
        System.out.print("1. Get all engines\n");
        System.out.print("2. Get engine by id\n");
        System.out.print("3. Create engine\n");
        System.out.print("4. Update engine\n");
        System.out.print("5. Delete engine\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printFuelEngineControllerMenu() {
        System.out.print("Fuel engine menu\n");
        System.out.print("1. Get all fuel engines\n");
        System.out.print("2. Get fuel engine by id\n");
        System.out.print("3. Create fuel engine\n");
        System.out.print("4. Update fuel engine\n");
        System.out.print("5. Delete fuel engine\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printElectricEngineControllerMenu() {
        System.out.print("Electric engine menu\n");
        System.out.print("1. Get all electric engines\n");
        System.out.print("2. Get electric engine by id\n");
        System.out.print("3. Create electric engine\n");
        System.out.print("4. Update electric engine\n");
        System.out.print("5. Delete electric engine\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printTransmissionControllerMenu() {
        System.out.print("Transmission menu\n");
        System.out.print("1. Get all transmission\n");
        System.out.print("2. Get transmission by id\n");
        System.out.print("3. Create transmission\n");
        System.out.print("4. Update transmission\n");
        System.out.print("5. Delete transmission\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printPostControllerMenu() {
        System.out.print("Post menu\n");
        System.out.print("1. Get all posts\n");
        System.out.print("2. Get post by id\n");
        System.out.print("3. Create post\n");
        System.out.print("4. Update post\n");
        System.out.print("5. Delete post\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printFuelEngineTableHead() {
        System.out.printf("%-3s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s |%-15s\n",
                "Id", "Fuel type", "Consumption", "NO. Cylinders",
                "CO2 emissions", "Layout", "Turbine", "Supercharge",
                "Stroke type", "Displacement");
    }

    public static void printFuelEngine(FuelEngine fuelEngine) {
        System.out.printf("%-3d |%-15s |%-15.2f |%-15d |%-15.2f |%-15s |%-15b |%-15b |%-15s |%-15.2f\n",
                fuelEngine.getId(), fuelEngine.getFuelType(), fuelEngine.getFuelConsumption(),
                fuelEngine.getNumberOfCylinders(), fuelEngine.getCO2Emissions(),
                fuelEngine.getEngineLayout(), fuelEngine.isHasTurbine(), fuelEngine.isHasSuperCharger(),
                fuelEngine.getStrokeType(), fuelEngine.getDisplacement());
    }

    public static void printFuelTypes() {
        for(int i = 0; i < FuelType.values().length; i++){
            System.out.printf("%d. %s\n", (i + 1), FuelType.values()[i]);
        }
    }

    public static void printEngineLayout() {
        for(int i = 0; i < EngineLayout.values().length; i++){
            System.out.printf("%d. %s\n", (i + 1), EngineLayout.values()[i]);
        }
    }

    public static void printStrokeType() {
        for(int i = 0; i < StrokeType.values().length; i++){
            System.out.printf("%d. %s\n", (i + 1), StrokeType.values()[i]);
        }
    }

    public static void printValidationErrors(List<String> validationErrors) {
        for (String error : validationErrors) {
            System.out.printf("    %s\n",error);
        }
    }

    public static void printPostTableHead() {
        System.out.printf("%-3s |%-15s |%-15s |%-15s |%-15s |%-15s\n",
                "Id", "Title", "Description", "Price",
                "Available", "Car Id");
    }

    public static void printPost(Post post) {
        System.out.printf("%-3s |%-15s |%-15s |%-15s |%-15s |%-15s\n",
                post.getId(), post.getTitle(), post.getDescription(),
                post.getPrice(), post.getAvailable(), post.getCar().getId());
    }

    public static void printFuelEngineUpdateOptions(){
        System.out.print("1. Update fuel type.\n");
        System.out.print("2. Update fuel consumption.\n");
        System.out.print("3. Update CO2 emissions.\n");
        System.out.print("4. Update engine layout.\n");
        System.out.print("5. Update stroke type.\n");
        System.out.print("6. Exit.\n");
        System.out.print("Choose a field to update: ");
    }
}
