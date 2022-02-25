package com.crystal.ovs.appRunner;

import com.crystal.ovs.appRunner.controllers.*;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class AdminAppRunner {
    private static boolean isRunning = true;

    public static void runAdminApp() {
        while(isRunning){
            OutputManager.printAdminAppMenu();
            int command = InputManager.readIntegerField();
            switch (command){
                case 1:
                    CarController.openCarController();
                case 2:
                    EngineController.openEngineController();
                case 3:
                    FuelEngineController.openFuelEngineController();
                case 4:
                    ElectricEngineController.openElectricEngineController();
                case 5:
                    TransmissionController.openTransmissionController();
                case 6:
                    PostController.openPostController();
                case 7:
                    isRunning = false;
            }

        }
    }
}
