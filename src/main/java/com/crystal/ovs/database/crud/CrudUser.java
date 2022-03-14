package com.crystal.ovs.database.crud;


import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.crystal.ovs.exceptions.WrongEmailOrPasswordException;

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
        List<User> userList = new ArrayList<>();
        String sql = "select * from " + USER_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet resultSet = databaseConnector.select(sql);
            while(resultSet.next()){
                userList.add(getUserFromResultSet(resultSet));
            }

            return userList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> selectAllAdminUsers() {

        List<User> userAdminList = new ArrayList<>();
        String sql = "select * from " + USER_TABLE_NAME + "where "+ USER_ROLE_COLUMN + "= " + UserRole.ADMIN +" ;";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet resultSet = databaseConnector.select(sql);
            while(resultSet.next()){
                userAdminList.add(getUserFromResultSet(resultSet));
            }

            return userAdminList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> selectAllClientUsers(){
        List<User> userClientList = new ArrayList<>();
        String sql = "select * from " + USER_TABLE_NAME + "where "+ USER_ROLE_COLUMN + "= " + UserRole.CLIENT +" ;";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet resultSet = databaseConnector.select(sql);
            while(resultSet.next()){
                userClientList.add(getUserFromResultSet(resultSet));
            }

            return userClientList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static User selectUserById(int id){
        String query = String.format("select * from " + USER_TABLE_NAME + " where " + USER_ID_COLUMN + " = " +  id);
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet resultSet = databaseConnector.select(query);
            if(resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // used for login
    // If no user with the given email is found return null.
    // If a user is found check if the password is the same as the given one.
    // If true return the User else throws WrongPasswordException
    public static User selectUserByCredentials(String email, String password) throws WrongEmailOrPasswordException {
        String query = String.format("select * from " + USER_TABLE_NAME + " where " + USER_EMAIL_COLUMN + " ='" +  email + "' AND " + USER_PASSWORD_COLUMN + " ='" + password + "';");
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet resultSet = databaseConnector.select(query);
            if(resultSet.next()) {
                return getUserFromResultSet(resultSet);
            } else {
                throw new WrongEmailOrPasswordException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        String sqlQuery = "update " + USER_TABLE_NAME + " SET " + USER_NAME_COLUMN + " = '"
                + newUser.getName() + "', " + USER_PASSWORD_COLUMN + " = '"
                + newUser.getPassword() +  "', " + USER_ROLE_COLUMN + " = '"
                + newUser.getRole() +  "', " + USER_EMAIL_COLUMN + " = '"
                + newUser.getEmail() +  "', " + " where id="
                + newUser.getId() +";";
        try{
            executeVoidQuery(sqlQuery);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteUser(int id){
        String sqlQuery = "delete from " + USER_TABLE_NAME + " where id=" + id +";";
        try{
            executeVoidQuery(sqlQuery);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    private static List<String> validateT(User user){
        return null;
    }

    private static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt(USER_ID_COLUMN),
                resultSet.getString(USER_NAME_COLUMN),
                resultSet.getString(USER_PASSWORD_COLUMN),
                UserRole.valueOf(resultSet.getString(USER_ROLE_COLUMN)),
                resultSet.getString(USER_EMAIL_COLUMN)
        );
    }

}
