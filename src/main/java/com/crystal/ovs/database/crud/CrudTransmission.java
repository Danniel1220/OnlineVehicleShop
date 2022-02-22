package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Transmission;
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
    private static void executeVoidQuery(String query) {
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: database CRUD operation failed!");
            e.printStackTrace();
        }
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

    public static void insertTransmissionTable(Transmission transmission){
        String sqlQuery = "insert into " + TRANSMISSION_TABLE_NAME + " (" + TRANSMISSION_COLUMN_NAME_TYPE
                + ", " + TRANSMISSION_COLUMN_NAME_GEARS
                + ") values ('" + transmission.getType() + "', " + transmission.getNumberOfGears() + ");";
        executeVoidQuery(sqlQuery);
    }

    public static void deleteTransmissionTable(int id){
        String sqlQuery = "delete from " + TRANSMISSION_TABLE_NAME + " where id=" + id +";";
        executeVoidQuery(sqlQuery);
    }

   /* public static void deleteAllRows(){
        String sqlQuery = "delete from " + TRANSMISSION_TABLE_NAME + ";";
        executeVoidQuery(sqlQuery);
    }*/

    public static void updateTransmissionTableInt(Transmission transmission){
        String sqlQuery = "update " + TRANSMISSION_TABLE_NAME + " SET " + TRANSMISSION_COLUMN_NAME_GEARS + " = "
                + transmission.getNumberOfGears() + " where id="
                + transmission.getId();
        executeVoidQuery(sqlQuery);

    }

    public static void updateTransmissionTableString(Transmission transmission){
        String sqlQuery = "update " + TRANSMISSION_TABLE_NAME + " SET " + TRANSMISSION_COLUMN_NAME_TYPE
                + " = '" + transmission.getType() + "' where id=" + transmission.getId();
        executeVoidQuery(sqlQuery);
    }

    public static void updateAllById(Transmission transmission){
        String sqlQuery = "update " + TRANSMISSION_TABLE_NAME + " SET " + TRANSMISSION_COLUMN_NAME_TYPE + " = '"
                + transmission.getType() + "', " + TRANSMISSION_COLUMN_NAME_GEARS + " = "
                + transmission.getNumberOfGears() + " where id="
                + transmission.getId() +";";
        executeVoidQuery(sqlQuery);
    }
}
