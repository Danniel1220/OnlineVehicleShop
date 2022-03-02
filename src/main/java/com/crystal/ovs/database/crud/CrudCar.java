package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.CarType;
import com.crystal.ovs.dao.ElectricEngine;
import com.crystal.ovs.dao.TractionType;
import com.crystal.ovs.dao.Transmission;
import com.crystal.ovs.database.DatabaseConnector;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static ResultSet selectAll() {
        String query = "SELECT * FROM " + CAR_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet selectListOfColumnsFromCar(ArrayList<String> columnList) {
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

    public static void insertIntoCar(Car car) {
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


    public static Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getInt(CAR_ID_COLUMN),
                resultSet.getString(CAR_BRAND_COLUMN),
                resultSet.getString(CAR_MODEL_COLUMN),
                resultSet.getString(CAR_VIN_COLUMN),
                resultSet.getInt(CAR_MANUFACTURING_YEAR_COLUMN),
                CarType.valueOf(resultSet.getString(CAR_TYPE_COLUMN)),
                resultSet.getInt(CAR_ENGINE_ID_COLUMN),
                resultSet.getInt(CAR_TRANSMISSION_ID_COLUMN),
                TractionType.valueOf(resultSet.getString(CAR_TRACTION_TYPE_COLUMN)),
                resultSet.getInt(CAR_NUMBER_OF_DOORS_COLUMN),
                Color.getColor(CAR_COLOR_COLUMN)
        );
    }

    public static List<Car> getAllCars() throws SQLException {
        List<Car> carsList = new ArrayList<>();

        ResultSet resultSet = selectAll();
        while (resultSet.next()) {
            carsList.add(getCarFromResultSet(resultSet));
        }
        return carsList;
    }

        public static java.util.List<String> validateCar (Car car){
            List<String> validationErrors = new ArrayList<>();

            if (car.getId() <= 0) {
                validationErrors.add("Id cannot be less than or equal to 0");
            }
            if (car.getBrand().isEmpty()) {
                validationErrors.add("Brand can't be empty");
            }
            if (car.getModel().isEmpty()) {
                validationErrors.add("Model can't be empty");
            }
            if (car.getVIN().isEmpty()) {
                validationErrors.add("VIN number can't be empty");
            }
            if (car.getManufacturingYear() < 2019) {
                validationErrors.add("This model of car is already out of manufacturing time");
            }
            if (car.getManufacturingYear() > 2023) {
                validationErrors.add("This model of car is not out yet");
            }
            if (car.getEngineId() <= 0) {
                validationErrors.add("EngineId can't be less than or equal to 0");
            }
            if (car.getTransmissionId() <= 0) {
                validationErrors.add("TransmissionId can't be less than or equal to 0");
            }
            if (car.getNumberOfDoors() < 1 || car.getNumberOfDoors() > 6) {
                validationErrors.add("A car can have a min number of doors of 1 and a maximum of 6");
            }
        /*if(!colorCodeValidation(car.getColor().toString())){
            validationErrors.add("The color introduced is not valid");
        }*/
            return validationErrors;
        }
        public static boolean colorCodeValidation (String s1){
            boolean b = false, b1 = false;
            String s2 = s1.substring(1, s1.length());
            if (s1.length() == 7)
                if (s1.charAt(0) == '#')
                    b1 = true;
            if (b1 == true)
                for (int i = 0; i < s2.length(); i++) {
                    char c = s2.charAt(i);
                    if (c != '#') {
                        if (s2.matches("[A-Fa-f0-9]{6}|[A-Fa-f0-9]{3}"))
                            return true;
                        else {
                            return false;
                        }
                    }
                }
            return false;
        }
}
