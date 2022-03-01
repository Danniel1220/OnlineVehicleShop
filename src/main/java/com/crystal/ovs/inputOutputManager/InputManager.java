package com.crystal.ovs.inputOutputManager;

import java.util.Scanner;

public class InputManager {
    private final static Scanner scanner = new Scanner(System.in);
    public static int readIntegerField() {
        if(scanner.hasNextInt()){
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        }
        else{
            scanner.next();
            return -1;
        }
    }

    public static float readFloatField() {
        if(scanner.hasNextFloat()){
            float value = scanner.nextFloat();
            scanner.nextLine();
            return value;
        }
        else{
            scanner.next();
            return -1;
        }
    }

    public static String readStringField() {
        return scanner.nextLine();
    }
}
