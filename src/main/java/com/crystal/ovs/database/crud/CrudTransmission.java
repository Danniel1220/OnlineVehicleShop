package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.Post;
import com.crystal.ovs.dao.Transmission;
import com.crystal.ovs.dao.TransmissionType;
import com.crystal.ovs.database.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudTransmission {
    private final static String TRANSMISSION_TABLE_NAME = "transmission";
    private final static String TRANSMISSION_COLUMN_NAME_ID = "id";
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

    public static List<Transmission> selectAllFromTransmissionTable(){
        List<Transmission> transmissionList = new ArrayList<>();
        String sql = "select * from " + TRANSMISSION_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet resultSet = databaseConnector.select(sql);
            while(resultSet.next()){
                transmissionList.add(getTransmissionFromResultSet(resultSet));
            }

            return transmissionList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Transmission selectAllByTransmissionId(int id) throws SQLException {
        String query = String.format("select * from " + TRANSMISSION_TABLE_NAME + " where " + TRANSMISSION_COLUMN_NAME_ID + " = " +  id);
        databaseConnector = DatabaseConnector.getInstance();
        ResultSet resultSet = databaseConnector.select(query);
        resultSet.next();
        return getTransmissionFromResultSet(resultSet);
    }

    public static Transmission getTransmissionFromResultSet(ResultSet resultSet) throws SQLException {
            return new Transmission(
                    resultSet.getInt(TRANSMISSION_COLUMN_NAME_ID),
                    TransmissionType.valueOf(resultSet.getString(TRANSMISSION_COLUMN_NAME_TYPE)),
                    resultSet.getInt(TRANSMISSION_COLUMN_NAME_GEARS)
            );
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

    public static void updateTransmissionTableNrOfGears(Transmission transmission){
        String sqlQuery = "update " + TRANSMISSION_TABLE_NAME + " SET " + TRANSMISSION_COLUMN_NAME_GEARS + " = "
                + transmission.getNumberOfGears() + " where id="
                + transmission.getId();
        executeVoidQuery(sqlQuery);

    }

    public static void updateTransmissionTableTransmissionType(Transmission transmission){
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
