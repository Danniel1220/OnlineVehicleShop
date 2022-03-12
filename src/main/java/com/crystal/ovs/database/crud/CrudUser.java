package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class CrudUser {
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_ID_COLUMN = "id";
    public static final String USER_NAME_COLUMN = "name";
    public static final String USER_PASSWORD_COLUMN = "password";
    public static final String USER_ROLE_COLUMN = "role";
    public static final String USER_EMAIL_COLUMN = "email";
    private static DatabaseConnector databaseConnector;

    private static void executeVoidQuery(String query) {
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            OutputManager.printMessage(OutputTextType.ERROR,"ERROR: database CRUD operation failed!");
            e.printStackTrace();
        }
    }

    public static List<User> selectAllUsers() {
        return null;
    }

    public static List<User> selectAllAdminUsers() {
        return null;
    }

    public static List<User> selectAllClientUsers(){
        return null;
    }

    public static User selectUserById(int id){
        return null;
    }

    // used for login
    // If no user with the given email is found return null.
    // If a user is found check if the password is the same as the given one.
    // If true return the User else throws WrongPasswordException
    public static User selectUserByCredentials(String email, String password){
        return null;
    }

    public static int insertUser(User user) {
        String query = "INSERT INTO " + USER_TABLE_NAME +
                "(" + USER_NAME_COLUMN + ", "
                + USER_PASSWORD_COLUMN + ", "
                + USER_ROLE_COLUMN + ", "
                + USER_EMAIL_COLUMN  + ") " +
                "VALUES ('" + user.getName() + "', '" + user.getPassword() + "', '" + user.getRole() + "', '" + user.getEmail() + "');";
        try {
            executeVoidQuery(query);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int updateUser(User newUser){
        return 0;
    }

    public static int deleteUser(int id){
        return 0;
    }

    private static List<String> validateT(User user){
        return null;
    }

    private static User getUserFromResultSet(ResultSet resultSet){
        return null;
    }

}
