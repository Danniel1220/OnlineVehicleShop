package com.crystal.ovs.inputOutputManager;

import com.crystal.ovs.dao.*;

import java.util.List;

public class OutputManager {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";


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

    /**
     * @param option should have a value of ERROR red text, WARNING yellow text, CONNECTION green text
     * @param message the message you would like to print
     */
    public static void printMessage(OutputTextType option, String message) {
        switch(option) {
            case ERROR: {
                System.out.println(TEXT_RED + message + TEXT_RESET);
                break;
            }
            case WARNING: {
                System.out.println(TEXT_YELLOW + message + TEXT_RESET);
                break;
            }
            case CONNECTION: {
                System.out.println(TEXT_GREEN + message + TEXT_RESET);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }

    }

    public static void printAdminAppMenu() {
        System.out.print("Menu\n");
        System.out.print("1. Car\n");
        System.out.print("2. Engine\n");
        System.out.print("3. Fuel engine\n");
        System.out.print("4. Electric engine\n");
        System.out.print("5. Transmission\n");
        System.out.print("6. Post\n");
        System.out.print("7. Users\n");
        System.out.print("8. Exit\n");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printClientAppMenu() {
        System.out.print("Menu\n");
        System.out.print("1. See all posts\n");
        System.out.print("2. Search post\n");
        System.out.print("3. Buy a car\n");
        System.out.print("4. See your transaction's history\n");
        System.out.print("5. Log out\n");
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
