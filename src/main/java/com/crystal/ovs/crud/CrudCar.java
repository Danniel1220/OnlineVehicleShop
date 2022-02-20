package com.crystal.ovs.crud;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.database.DatabaseConnector;
import java.sql.ResultSet;

/**
 *  This class contains all CRUD operations for Car table
 */

public class CrudCar {
    private static final String CAR_TABLE_NAME = "car";
    private static final String CAR_ID_COLUMN = "id";
    private static final String CAR_BRAND_COLUMN = "brand";
    private static final String CAR_MODEL_COLUMN = "model";
    private static final String CAR_VIN_COLUMN = "VIN";
    private static final String CAR_MANUFACTURING_YEAR_COLUMN = "manufacturingYear";
    private static final String CAR_TYPE_COLUMN = "carType";
    private static final String CAR_ENGINE_ID_COLUMN = "engineId";
    private static final String CAR_TRANSMISSION_COLUMN = "transmissionId";
    private static final String CAR_TRACTION_TYPE_COLUMN = "tractionType";
    private static final String CAR_NUMBER_OF_DOORS_COLUMN = "numberOfDoors";
    private static final String CAR_COLOR_COLUMN = "Color";
    private static DatabaseConnector databaseConnector;

    public static int getNumberOfRows() {
        String query = "SELECT COUNT(*) AS TABLE_ROWS FROM " + CAR_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            ResultSet result = databaseConnector.select(query);
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void deleteById(int id) {
        String query = "DELETE FROM " + CAR_TABLE_NAME + " WHERE " + CAR_ID_COLUMN + " = " + id + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: deletion failed!");
            e.printStackTrace();
        }
    }

    public static void updateAllById(Car car) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_BRAND_COLUMN + " = '" + car.getBrand() + "', " +
                CAR_MODEL_COLUMN + " = '" + car.getModel() + "', " +
                CAR_VIN_COLUMN + " = '" + car.getVIN() + "', " +
                CAR_MANUFACTURING_YEAR_COLUMN + " = " + car.getManufacturingYear() + ", " +
                CAR_TYPE_COLUMN + " = '" + car.getCarType() + "', " +
                CAR_ENGINE_ID_COLUMN + " = " + car.getEngineId() + ", " +
                CAR_TRANSMISSION_COLUMN + " = '" + car.getTransmissionId() + "', " +
                CAR_TRACTION_TYPE_COLUMN + " = '" + car.getTractionType() + "', " +
                CAR_NUMBER_OF_DOORS_COLUMN + " = " + car.getNumberOfDoors() + ", " +
                CAR_COLOR_COLUMN + " = '" + car.getColor() + "'" +
                " WHERE id = " + car.getId() + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: update failed!");
            e.printStackTrace();
        }
    }
}
