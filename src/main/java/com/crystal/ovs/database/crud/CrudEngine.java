package com.crystal.ovs.database.crud;


import com.crystal.ovs.dao.Engine;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CrudEngine {

    private static final String ENGINE_TABLE_NAME = "engine";
    private static final String ENGINE_ID_COLUMN = "id";
    private static final String ENGINE_FUEL_ENGINE_ID_COLUMN = "fuelEngineId";
    private static final String ENGINE_ELECTRIC_ENGINE_ID_COLUMN = "electricEngineId";
    private static final String ENGINE_HORSE_POWER_COLUMN = "horsePower";
    private static final String ENGINE_TORQUE_COLUMN = "torque";
    private static DatabaseConnector databaseConnector;

    public static int getNumberOfRows() {
        String query = "SELECT COUNT(*) AS TABLE_ROWS FROM " + ENGINE_TABLE_NAME + ";";
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
            OutputManager.printMessage(OutputTextType.ERROR,"ERROR: database CRUD operation failed!");
            e.printStackTrace();
        }
    }

    public static ResultSet executeResultSetQuery(String query){
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(query);
        } catch (Exception e) {
            OutputManager.printMessage(OutputTextType.ERROR,"ERROR: database CRUD operation failed!");
            e.printStackTrace();
        }
        return null;
    }
    public static List<Engine> selectAllEngine() {
        List<Engine> engineList=new ArrayList<>();
        String query = "SELECT * FROM " + ENGINE_TABLE_NAME + ";";
        try {
            return getAllEngine(Objects.requireNonNull(executeResultSetQuery(query)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Engine selectEngineById(int id) {
        String query = "SELECT * FROM " + ENGINE_TABLE_NAME + " WHERE " + ENGINE_ID_COLUMN + " = " + id + ";";
        try {
            ResultSet resultSet = Objects.requireNonNull(executeResultSetQuery(query));
            if(resultSet.next()){
                return getEngineFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet selectListOfColumnsFromEngine(ArrayList<String> columnList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String eachString : columnList) {
            stringBuilder.append(eachString).append(",");
        }
        String commaseparatedlist = stringBuilder.toString();
        if (commaseparatedlist.length() > 0) {
            commaseparatedlist = commaseparatedlist.substring(0, commaseparatedlist.length() - 1);
        }
        String query = "SELECT " + commaseparatedlist + " FROM " + ENGINE_TABLE_NAME + ";";
        System.out.println(query);
        try {
            databaseConnector = DatabaseConnector.getInstance();
            return databaseConnector.select(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void insertEngine(Engine engine) throws ValidationException {
        List<String> validationErrors = validateEngine(engine);
        if(validationErrors.size()>0){
            throw new ValidationException(validationErrors);
        }
        String querySet = "SET FOREIGN_KEY_CHECKS=0;\n";
        String query = "INSERT INTO " + ENGINE_TABLE_NAME +
                "(" + ENGINE_FUEL_ENGINE_ID_COLUMN + ", "
                + ENGINE_ELECTRIC_ENGINE_ID_COLUMN + ", "
                + ENGINE_HORSE_POWER_COLUMN + ", "
                + ENGINE_TORQUE_COLUMN +
                ") VALUES ('"
                + engine.getFuelEngineId() + "', '"
                + engine.getElectricEngineId() + "', '"
                + engine.getHorsePower() + "', '"
                + engine.getTorque()
                + "');";
        executeVoidQuery(querySet);
        executeVoidQuery(query);
    }

    public static void deleteEngine(int id) {
        String query = "DELETE FROM " + ENGINE_TABLE_NAME + " WHERE " + ENGINE_ID_COLUMN + " = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateAllEngineById(Engine engine) {
        String query = "UPDATE " + ENGINE_TABLE_NAME + " SET " +
                ENGINE_FUEL_ENGINE_ID_COLUMN + " = '" + engine.getFuelEngineId() + "', " +
                ENGINE_ELECTRIC_ENGINE_ID_COLUMN + " = '" + engine.getElectricEngineId() + "', " +
                ENGINE_HORSE_POWER_COLUMN + " = '" + engine.getHorsePower() + "', " +
                ENGINE_TORQUE_COLUMN + " = '" + engine.getTorque() + "'" +
                " WHERE id = " + engine.getId() + ";";
        executeVoidQuery(query);
    }

    public static void updateFuelEngineById(int id, int fuelEngineId) {
        String query = "UPDATE " + ENGINE_TABLE_NAME + " SET " +
                ENGINE_FUEL_ENGINE_ID_COLUMN + " = '" + fuelEngineId +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateEngineElectricById(int id, int electicEngineId) {
        String query = "UPDATE " + ENGINE_TABLE_NAME + " SET " +
                ENGINE_ELECTRIC_ENGINE_ID_COLUMN + " = '" + electicEngineId +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateEngineHorsePowerById(int id, int horsePower) {
        String query = "UPDATE " + ENGINE_TABLE_NAME + " SET " +
                ENGINE_HORSE_POWER_COLUMN + " = '" + horsePower +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }

    public static void updateEngineTorqueById(int id, int torque) {
        String query = "UPDATE " + ENGINE_TABLE_NAME + " SET " +
                ENGINE_TORQUE_COLUMN + " = '" + torque +
                "' WHERE id = " + id + ";";
        executeVoidQuery(query);
    }




    public static void deleteByTorque( int torque) {
        String query = "DELETE FROM " + ENGINE_TABLE_NAME + " WHERE " + ENGINE_TORQUE_COLUMN + " = " + torque + ";";
        executeVoidQuery(query);
    }
    public static void deleteByHorsePower(int horsePower) {
        String query = "DELETE FROM " + ENGINE_TABLE_NAME + " WHERE " + ENGINE_HORSE_POWER_COLUMN+ " = " + horsePower + ";";
        executeVoidQuery(query);
    }
    public static void deleteByFuelEngineId(int fuelEngineId) {
        String query = "DELETE FROM " + ENGINE_TABLE_NAME + " WHERE " + ENGINE_FUEL_ENGINE_ID_COLUMN+ " = " + fuelEngineId + ";";
        executeVoidQuery(query);
    }
    public static void deleteByElectricEngineId(int electricEngineId) {
        String query = "DELETE FROM " + ENGINE_TABLE_NAME + " WHERE " + ENGINE_ELECTRIC_ENGINE_ID_COLUMN+ " = " + electricEngineId + ";";
        executeVoidQuery(query);
    }
    public static Engine getEngineFromResultSet(ResultSet resultSet) throws SQLException {
        return new Engine(
                resultSet.getInt(ENGINE_ID_COLUMN),
                resultSet.getInt(ENGINE_FUEL_ENGINE_ID_COLUMN),
                resultSet.getInt(ENGINE_ELECTRIC_ENGINE_ID_COLUMN),
                resultSet.getInt(ENGINE_HORSE_POWER_COLUMN),
                resultSet.getInt(ENGINE_TORQUE_COLUMN)


        );
    }

    private static List<Engine> getAllEngine(ResultSet resultSet) throws SQLException {
        List<Engine> engineList = new ArrayList<>();
        while (resultSet.next()) {
            engineList.add(getEngineFromResultSet(resultSet));
        }
        return engineList;
    }

    public static java.util.List<String> validateEngine (Engine engine) {
        List<String> validationErrors = new ArrayList<>();

        if (engine.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if (engine.getFuelEngineId() <= 0) {
            validationErrors.add("Fuel Engine Id must be less than 0");
        }
        if (engine.getElectricEngineId() <= 0) {
            validationErrors.add("Electric Engine Id must be less than 0");
        }
        if (engine.getHorsePower() < 3) {
            validationErrors.add(" Horse power(HP) must be less than 60 ");
        }

        if (engine.getTorque() <= 0) {
            validationErrors.add("Torque must be less than 0");
        }
        return validationErrors;
    }




}
