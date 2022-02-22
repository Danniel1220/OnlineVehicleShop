package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.CarType;
import com.crystal.ovs.dao.TractionType;
import com.crystal.ovs.database.DatabaseConnector;
import java.awt.*;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    private static final String CAR_TRANSMISSION_ID_COLUMN = "transmissionId";
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

    private static void executeVoidQuery(String query) {
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: database CRUD operation failed!");
            e.printStackTrace();
        }
    }
    public static ResultSet selectAllFromCar(){
        String query = "SELECT * FROM " + CAR_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet selectListOfColumnsFromCar(ArrayList<String> columnList){
        StringBuilder stringBuilder = new StringBuilder();
        for (String eachString : columnList) {
            stringBuilder.append(eachString).append(",");
        }
        String commaseparatedlist = stringBuilder.toString();
        if (commaseparatedlist.length() > 0) {
            commaseparatedlist = commaseparatedlist.substring(0, commaseparatedlist.length() - 1);
        }
        String query = "SELECT " + commaseparatedlist + " FROM " + CAR_TABLE_NAME + ";";
        System.out.println(query);
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertIntoCar(Car car){
        String querySet = "SET FOREIGN_KEY_CHECKS=0;\n";
        String query = "INSERT INTO " + CAR_TABLE_NAME + "(" +
                CAR_BRAND_COLUMN + ", " + CAR_MODEL_COLUMN + ", " +
                CAR_VIN_COLUMN + ", " + CAR_MANUFACTURING_YEAR_COLUMN + ", " +
                CAR_TYPE_COLUMN + ", " + CAR_ENGINE_ID_COLUMN + ", " +
                CAR_TRANSMISSION_ID_COLUMN + ", " + CAR_TRACTION_TYPE_COLUMN + ", " +
                CAR_NUMBER_OF_DOORS_COLUMN + ", " + CAR_COLOR_COLUMN + ") VALUES ('" + car.getBrand() + "', '" +
                car.getModel() + "', '" + car.getVIN() + "', " + car.getManufacturingYear() + ", '" +
                car.getCarType() + "', " + car.getEngineId() + ", " + car.getTransmissionId() + ", '" +
                car.getTractionType() + "', " + car.getNumberOfDoors() + ", '" + car.getColor().toString() + "');";

        executeVoidQuery(querySet);
        executeVoidQuery(query);
    }

    public static void deleteById(int id) {
        String query = "DELETE FROM " + CAR_TABLE_NAME + " WHERE " + CAR_ID_COLUMN + " = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateAllById(Car car) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_BRAND_COLUMN + " = '" + car.getBrand() + "', " +
                CAR_MODEL_COLUMN + " = '" + car.getModel() + "', " +
                CAR_VIN_COLUMN + " = '" + car.getVIN() + "', " +
                CAR_MANUFACTURING_YEAR_COLUMN + " = " + car.getManufacturingYear() + ", " +
                CAR_TYPE_COLUMN + " = '" + car.getCarType() + "', " +
                CAR_ENGINE_ID_COLUMN + " = " + car.getEngineId() + ", " +
                CAR_TRANSMISSION_ID_COLUMN + " = " + car.getTransmissionId() + ", " +
                CAR_TRACTION_TYPE_COLUMN + " = '" + car.getTractionType() + "', " +
                CAR_NUMBER_OF_DOORS_COLUMN + " = " + car.getNumberOfDoors() + ", " +
                CAR_COLOR_COLUMN + " = '" + car.getColor() + "'" +
                " WHERE id = " + car.getId() + ";";
        executeVoidQuery(query);
    }

    public static void updateCarBrandById(int id, String brand) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_BRAND_COLUMN + " = '" + brand +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarModelById(int id, String model) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_MODEL_COLUMN + " = '" + model +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarVINById(int id, String VIN) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_VIN_COLUMN + " = '" + VIN +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarManufacturingYearById(int id, int manufacturingYear) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_MANUFACTURING_YEAR_COLUMN + " = " + manufacturingYear +
                " WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarTypeById(int id, CarType carType) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_TYPE_COLUMN + " = '" + carType +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarEngineIdById(int id, int engineId) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_ENGINE_ID_COLUMN + " = " + engineId +
                " WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarTransmissionIdById(int id, int transmissionId) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_TRANSMISSION_ID_COLUMN + " = " + transmissionId +
                " WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarTractionTypeById(int id, TractionType tractionType) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_TRACTION_TYPE_COLUMN + " = '" + tractionType +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarNumberOfDoorsById(int id, int numberOfDoors) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_NUMBER_OF_DOORS_COLUMN + " = " + numberOfDoors +
                " WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateCarColorById(int id, Color color) {
        String query = "UPDATE " + CAR_TABLE_NAME + " SET " +
                CAR_COLOR_COLUMN + " = '" + color +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }
}
