package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class EngineController {
    private static boolean isInEngineController = true;

    public static void openEngineController(){
        while(isInEngineController){
            OutputManager.printEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    getAllEngines();
                    break;
                case 2:
                    getEngineById();
                    break;
                case 3:
                    createEngine();
                    break;
                case 4:
                    updateEngine();
                    break;
                case 5:
                    deleteEngine();
                    break;
                case 6:
                    isInEngineController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllEngines() {
    }

    private static void getEngineById() {
    }

    private static void createEngine() {
    }

    private static void updateEngine() {
    }

    private static void deleteEngine() {
    }
}
