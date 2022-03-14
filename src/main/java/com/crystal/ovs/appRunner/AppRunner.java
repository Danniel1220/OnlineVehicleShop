package com.crystal.ovs.appRunner;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import com.crystal.ovs.database.crud.CrudUser;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppRunner {
    public static boolean isRunning = true;

    /**
     * Main menu of the application
     */
    public static void runApp(){
        while(isRunning){
            OutputManager.printAppMenu();
            int command = InputManager.readIntegerField();

            switch (command){
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    /**
     * Request account's email and password.
     * Checking if the account information is correct.
     * If so launch the corresponding menu.
     * Other-wise print errors.
     */
    private static void login() {
        OutputManager.printMessage("Login");
        OutputManager.printLabel("Email");
        String email = InputManager.readStringField();
        OutputManager.printLabel("Password");
        String password = InputManager.readStringField();

        try {
            User user = CrudUser.selectUserByCredentials(email, password);
            if(user != null) {
                List<String> validationErrors = validateAccountInformationForLogin(user);
                if(!hasErrors(validationErrors)) {
                    if(user.getRole() == UserRole.ADMIN) {
                        AdminAppRunner adminAppRunner = new AdminAppRunner(user);
                        adminAppRunner.runAdminApp();
                    } else {
                        ClientAppRunner clientAppRunner = new ClientAppRunner(user);
                        clientAppRunner.runClientApp();
                    }
                } else {
                    OutputManager.printMessage(OutputTextType.WARNING, "The information introduced is not correct!");
                }
            } else {
                OutputManager.printMessage(OutputTextType.ERROR, "The email or password entered is wrong!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Requesting the name, email and the password for the new account.
     * Creating user object with the information introduced.
     * Running the validation for user object.
     * If there are no errors insert the new account into the database.
     * Other-wise show errors.
     */
    private static void createAccount() {
        OutputManager.printMessage("Create account:");
        OutputManager.printLabel("Name");
        String name = InputManager.readStringField();
        OutputManager.printLabel("Email");
        String email = InputManager.readStringField();
        OutputManager.printLabel("Password");
        String password = InputManager.readStringField();

        try {
            User user = new User(0, name, password, UserRole.CLIENT, email);
            List<String> validationErrors = validateAccountInformationForRegistration(user);
            if(!hasErrors(validationErrors)) {
                CrudUser.insertUser(user);
                OutputManager.printMessage(OutputTextType.CONNECTION, "Account created!");
            }
        } catch (Exception e) {
            OutputManager.printMessage(OutputTextType.ERROR, "There was a problem creating your account!");
            e.printStackTrace();
        }
    }

    /**
     * Checking inserted account information to see if the account creation is possible.
     * All requested fields are mandatory.
     * Both email and username must be unique.
     * @param user object containing register information introduced by the user.
     * @return list of validation errors.
     */
    private static List<String> validateAccountInformationForRegistration(User user) {
        List<String> validationErrors = new ArrayList<>();

        if(user.getEmail().isEmpty() || user.getEmail().isBlank() || user.getEmail() == null) {
            validationErrors.add("ERROR: You must enter an email!");
        }
        if(user.getName().isEmpty() || user.getName().isBlank() || user.getName() == null) {
            validationErrors.add("ERROR: You must enter an username!");
        }
        if(user.getPassword().isEmpty() || user.getPassword().isBlank() || user.getPassword() == null) {
            validationErrors.add("ERROR: You must enter a password!");
        }

        List<User> existingUsers = CrudUser.selectAllUsers();
        for(User existingUser : Objects.requireNonNull(existingUsers)) {
            if(existingUser.getEmail().equals(user.getEmail())) {
                validationErrors.add("ERROR: Email already taken!");
                break;
            }
            if(existingUser.getName().equals(user.getName())) {
                validationErrors.add("ERROR: Username already taken!");
                break;
            }
        }
        return validationErrors;
    }

    /**
     * Checks if account's information introduced corresponds to an existing account.
     * @param user object containing account information introduced.
     * @return list of validation errors.
     */
    private static List<String> validateAccountInformationForLogin(User user) {
        List<String> validationErrors = new ArrayList<>();
        if(user == null) {
            validationErrors.add("ERROR: email or password incorrect!");
        }
        return validationErrors;
    }

    /**
     * Print in console all validation errors.
     * @param validationErrors list of errors got from validation to be printed
     * @return true <= there were validation errors | false <= other-wise
     */
    private static boolean hasErrors(List<String> validationErrors) {
        if (validationErrors.size() > 0) {
            for (String error : validationErrors) {
                OutputManager.printMessage(OutputTextType.ERROR,error);
            }
            return true;
        }
        return false;
    }
}
