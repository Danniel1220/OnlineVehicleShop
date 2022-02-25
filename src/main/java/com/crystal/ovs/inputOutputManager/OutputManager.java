package com.crystal.ovs.inputOutputManager;

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
        System.out.print("Car menu\n");
        System.out.print("1. Get all fuel engines\n");
        System.out.print("2. Get fuel engine by id\n");
        System.out.print("3. Create fuel engine\n");
        System.out.print("4. Update fuel engine\n");
        System.out.print("5. Delete fuel engine\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printElectricEngineControllerMenu() {
        System.out.print("Car menu\n");
        System.out.print("1. Get all electric engines\n");
        System.out.print("2. Get electric engine by id\n");
        System.out.print("3. Create electric engine\n");
        System.out.print("4. Update electric engine\n");
        System.out.print("5. Delete electric engine\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printTransmissionControllerMenu() {
        System.out.print("Car menu\n");
        System.out.print("1. Get all transmission\n");
        System.out.print("2. Get transmission by id\n");
        System.out.print("3. Create transmission\n");
        System.out.print("4. Update transmission\n");
        System.out.print("5. Delete transmission\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }

    public static void printPostControllerMenu() {
        System.out.print("Car menu\n");
        System.out.print("1. Get all posts\n");
        System.out.print("2. Get post by id\n");
        System.out.print("3. Create post\n");
        System.out.print("4. Update post\n");
        System.out.print("5. Delete post\n");
        System.out.print("6. Back");
        System.out.print("\nChose a command (enter the command number): ");
    }
}
