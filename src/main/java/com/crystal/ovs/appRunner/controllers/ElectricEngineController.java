package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.ElectricEngine;
import com.crystal.ovs.database.crud.CrudElectricEngine;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

import java.util.List;

public class ElectricEngineController {
    private static boolean isInElectricEngineController = true;

    public static void openElectricEngineController(){
        while(isInElectricEngineController){
            OutputManager.printElectricEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    getAllElectricEngines();
                    break;
                case 2:
                    getElectricEnginesById();
                    break;
                case 3:
                    createElectricEngine();
                    break;
                case 4:
                    updateElectricEngine();
                    break;
                case 5:
                    deleteElectricEngine();
                    break;
                case 6:
                    isInElectricEngineController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllElectricEngines() {
    }

    private static void getElectricEnginesById() {
    }

    private static void createElectricEngine() {
    }

    private static void updateElectricEngine() {
    }

    private static void deleteElectricEngine() {
    }
}
