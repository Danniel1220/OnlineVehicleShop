package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.User;

import java.sql.ResultSet;
import java.util.List;

public class CrudUser {
    public static List<User> selectAllUsers(){
        return null;
    }

    public static List<User> selectAllAdminUsers(){
        return null;
    }

    public static List<User> selectAllClientUsers(){
        return null;
    }

    public static User selectUserById(int id){
        return null;
    }

    //used for login
    //If no user with the given email is found return null.
    //If a user is found check if the password is the same as the given one.
    // If true return the User else throws WrongPasswordException
    public static User selectUserByCredentials(String email, String password){
        return null;
    }

    //check email is unique
    public static int insertUser(User user){
        return 0;
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
