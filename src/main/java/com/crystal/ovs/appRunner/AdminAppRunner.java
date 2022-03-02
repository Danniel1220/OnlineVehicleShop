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
                    break;
                case 2:
                    EngineController.openEngineController();
                    break;
                case 3:
                    FuelEngineController.openFuelEngineController();
                    break;
                case 4:
                    ElectricEngineController.openElectricEngineController();
                    break;
                case 5:
                    TransmissionController.openTransmissionController();
                    break;
                case 6:
                    PostController.openPostController();
                    break;
                case 7:
                    isRunning = false;
                    break;
            }

        }
    }
}
