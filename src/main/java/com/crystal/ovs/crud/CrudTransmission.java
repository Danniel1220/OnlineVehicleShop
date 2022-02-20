package com.crystal.ovs.crud;

import com.crystal.ovs.database.DatabaseConnector;

import java.sql.ResultSet;

public class CrudTransmission {
    private final static String TRANSMISSION_TABLE_NAME = "transmission";
    private final static String TRANSMISSION_COLUMN_NAME_TYPE = "transmissionType";
    private final static String TRANSMISSION_COLUMN_NAME_GEARS = "numberOfGears";
    private static DatabaseConnector databaseConnector;

    public static int getNumberOfRows() {
        String query = "SELECT COUNT(*) AS TABLE_ROWS FROM " + TRANSMISSION_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet result = databaseConnector.select(query);
            while (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static ResultSet selectAllFromTransmissionTable(){
        String sql = "select * from " + TRANSMISSION_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void insertTransmissionTable(String type, int gears){
        String sqlQuery = "insert into " + TRANSMISSION_TABLE_NAME + " (" + TRANSMISSION_COLUMN_NAME_TYPE + ", " + TRANSMISSION_COLUMN_NAME_GEARS + ") values ('" + type + "', " + gears + ");";
        try{
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(sqlQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteTransmissionTable(int id){
        String sqlQuery = "delete from " + TRANSMISSION_TABLE_NAME + " where id=" + id +";";
        try{
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(sqlQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /* public static void deleteAllRows(){
        String sqlQuery = "delete from " + TRANSMISSION_TABLE_NAME + ";";
        try{
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(sqlQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public static void updateTransmissionTableInt(String column, int id, int value){
        String sqlQuery = "update " + TRANSMISSION_TABLE_NAME + " SET " + column + " = " + value + " where id=" + id;
        try{
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(sqlQuery);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateTransmissionTableString(String column, int id, String value){
        String sqlQuery = "update " + TRANSMISSION_TABLE_NAME + " SET " + column + " = '" + value + "' where id=" + id;
        try{
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(sqlQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateAllById(int id, String type, int numberOfGears){
        String sqlQuery = "update " + TRANSMISSION_TABLE_NAME + " SET " + TRANSMISSION_COLUMN_NAME_TYPE + " = '"
                + type + "', " + TRANSMISSION_COLUMN_NAME_GEARS + " = " + numberOfGears + " where id=" +id +";";
        try{
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(sqlQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
