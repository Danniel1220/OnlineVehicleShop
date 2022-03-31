package com.crystal.ovs.appRunner;

import com.crystal.ovs.appRunner.controllers.*;
import com.crystal.ovs.dao.User;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class AdminAppRunner {
    private User user;

    public AdminAppRunner(User user) {
        this.user = user;
    }

    /**
     * Admin main menu
     */
    public void runAdminApp() {
        boolean isInAdminController = true;
        while(isInAdminController){
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
                    UserController.openUserController();
                    break;
                case 8:
                    isInAdminController = false;
                    break;
            }
        }
    }
}
