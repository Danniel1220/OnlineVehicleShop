package com.crystal.ovs.crud;

import com.crystal.ovs.dao.ElectricEngine;
import com.crystal.ovs.database.DatabaseConnector;
import java.sql.*;

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

    public static void insertEngine(ElectricEngine electricEngine) {
        String query = "INSERT INTO " + ELECTRIC_ENGINE_TABLE_NAME +
                "(" + ELECTRIC_ENGINE_TYPE_COLUMN + ", "
                + ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN + ", "
                + ELECTRIC_ENGINE_RANGE_COLUMN + ") " +
                "VALUES ('" + electricEngine.getType() + "', " + electricEngine.getBatteryCapacity() + ", " + electricEngine.getRange() + ");";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: insertion failed!");
            e.printStackTrace();
        }
    }

    public static void deleteById(int id) {
        String query = "DELETE FROM " + ELECTRIC_ENGINE_TABLE_NAME + " WHERE " + ELECTRIC_ENGINE_ID_COLUMN + " = " + id + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: deletion failed!");
            e.printStackTrace();
        }
    }

    public static void updateAllById(ElectricEngine electricEngine) {
        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_TYPE_COLUMN + " = '" + electricEngine.getType() + "', " +
                ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN + " = " + electricEngine.getBatteryCapacity() + ", " +
                ELECTRIC_ENGINE_RANGE_COLUMN + " = " + electricEngine.getRange() +
                " WHERE id = " + electricEngine.getId() + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: update failed!");
            e.printStackTrace();
        }
    }

    public static void updateEngineTypeById(int id, String engineType) {
        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_TYPE_COLUMN + " = '" + engineType + "'" +
                " WHERE id = " + id + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: update failed!");
            e.printStackTrace();
        }
    }

    public static void updateBatteryCapacityById(int id, int batteryCapacity) {
        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_BATTERY_CAPACITY_COLUMN + " = " + batteryCapacity +
                " WHERE id = " + id + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: update failed!");
            e.printStackTrace();
        }
    }

    public static void updateEngineRangeById(int id, int range) {
        String query = "UPDATE " + ELECTRIC_ENGINE_TABLE_NAME + " SET " +
                ELECTRIC_ENGINE_RANGE_COLUMN + " = " + range +
                " WHERE id = " + id + ";";
        try {
            databaseConnector = DatabaseConnector.getInstance();
            databaseConnector.execute(query);
        } catch (Exception e) {
            System.out.println("ERROR: update failed!");
            e.printStackTrace();
        }
    }
}
