package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.ElectricEngine;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains CRUD operations methods for Electric Engine table
 */

public class CrudElectricEngine {
    private static final String ELECTRIC_ENGINE_TABLE_NAME = "electricengine";
    private static final String ELECTRIC_ENGINE_ID_COLUMN = "id";
    private static final String ELECTRIC_ENGINE_TYPE_COLUMN = "engineType";
    private static final String ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN = "batteryCapacity";
    private static final String ELECTRIC_ENGINE_RANGE_COLUMN = "engineRange";
    private static DatabaseConnector databaseConnector;

    public static int getNumberOfRows() {
        String query = "SELECT COUNT(*) AS TABLE_ROWS FROM " + ELECTRIC_ENGINE_TABLE_NAME + ";";
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
        String query = "SELECT * FROM " + ELECTRIC_ENGINE_TABLE_NAME + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet selectById(int id) {
        String query = "SELECT * FROM " + ELECTRIC_ENGINE_TABLE_NAME + " WHERE " + ELECTRIC_ENGINE_ID_COLUMN + " = " + id + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ElectricEngine getElectricEngineFromResultSet(ResultSet resultSet) throws SQLException {
        return new ElectricEngine (
                resultSet.getInt(ELECTRIC_ENGINE_ID_COLUMN),
                resultSet.getString(ELECTRIC_ENGINE_TYPE_COLUMN),
                resultSet.getInt(ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN),
                resultSet.getInt(ELECTRIC_ENGINE_RANGE_COLUMN)
        );
    }

    public static List<ElectricEngine> getAllElectricEngines() throws SQLException {
        List<ElectricEngine> electricEnginesList = new ArrayList<>();

        ResultSet resultSet = selectAll();
        while(resultSet.next()) {
            electricEnginesList.add(getElectricEngineFromResultSet(resultSet));
        }
        return electricEnginesList;
    }

    public static void insertEngine(ElectricEngine electricEngine) throws ValidationException {
        List<String> validationErrors = validateElectricEngine(electricEngine);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }

        String query = "INSERT INTO " + ELECTRIC_ENGINE_TABLE_NAME +
                "(" + ELECTRIC_ENGINE_TYPE_COLUMN + ", "
                + ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN + ", "
                + ELECTRIC_ENGINE_RANGE_COLUMN + ") " +
                "VALUES ('" + electricEngine.getType() + "', " + electricEngine.getBatteryCapacity() + ", " + electricEngine.getRange() + ");";
        executeVoidQuery(query);
    }

    public static void deleteById(int id) {
        String query = "DELETE FROM " + ELECTRIC_ENGINE_TABLE_NAME + " WHERE " + ELECTRIC_ENGINE_ID_COLUMN + " = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateAllById(ElectricEngine electricEngine) throws ValidationException {
        List<String> validationErrors = validateElectricEngine(electricEngine);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }

        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_TYPE_COLUMN + " = '" + electricEngine.getType() + "', " +
                ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN + " = " + electricEngine.getBatteryCapacity() + ", " +
                ELECTRIC_ENGINE_RANGE_COLUMN + " = " + electricEngine.getRange() +
                " WHERE id = " + electricEngine.getId() + ";";
        executeVoidQuery(query);
    }

    public static void updateEngineTypeById(int id, String engineType) {
        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_TYPE_COLUMN + " = '" + engineType + "'" +
                " WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateBatteryCapacityById(int id, int batteryCapacity) {
        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN + " = " + batteryCapacity +
                " WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateEngineRangeById(int id, int range) {
        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_RANGE_COLUMN + " = " + range +
                " WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static List<String> validateElectricEngine(ElectricEngine electricEngine){
        List<String> validationErrors = new ArrayList<>();

        if(electricEngine.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(electricEngine.getType().isEmpty()) {
            validationErrors.add("Electric engine type cannot be empty");
        }
        if(electricEngine.getBatteryCapacity() <= 0) {
            validationErrors.add("Battery capacity cannot be less than or equal to 0");
        }
        if(electricEngine.getRange() <= 0) {
            validationErrors.add("Range cannot be less than or equal to 0");
        }

        return validationErrors;
    }
}
