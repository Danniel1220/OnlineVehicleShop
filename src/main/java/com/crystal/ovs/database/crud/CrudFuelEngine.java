package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.EngineLayout;
import com.crystal.ovs.dao.FuelEngine;
import com.crystal.ovs.dao.FuelType;
import com.crystal.ovs.dao.StrokeType;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudFuelEngine {
    private static DatabaseConnector connector;

    static {
        connector = DatabaseConnector.getInstance();
    }

    private static final String FUEL_ENGINE_TABLE_NAME = "fuelengine";

    private static final String FUEL_ENGINE_ID_COLUMN = "id";
    private static final String FUEL_ENGINE_FUEL_TYPE_COLUMN = "fuelType";
    private static final String FUEL_ENGINE_FUEL_CONSUMPTION_COLUMN = "fuelConsumption";
    private static final String FUEL_ENGINE_NUMBER_OF_CYLINDERS_COLUMN = "numberOfCylinders";
    private static final String FUEL_ENGINE_CO2_EMISSIONS_COLUMN = "CO2Emissions";
    private static final String FUEL_ENGINE_LAYOUT_COLUMN = "engineLayout";
    private static final String FUEL_ENGINE_HAS_TURBINE_COLUMN = "hasTurbine";
    private static final String FUEL_ENGINE_HAS_SUPERCHARGE_COLUMN = "hasSuperCharger";
    private static final String FUEL_ENGINE_STROKE_TYPE_COLUMN = "strokeType";
    private static final String FUEL_ENGINE_DISPLACEMENT_COLUMN = "displacement";

    public static List<FuelEngine> selectAllFuelEngines() throws SQLException {
        List<FuelEngine> fuelEngineList = new ArrayList<>();

        String query = String.format("SELECT * FROM %s;", FUEL_ENGINE_TABLE_NAME);
        ResultSet resultSet = connector.select(query);
        while(resultSet.next()){
            fuelEngineList.add(getFuelEngineFromResultSet(resultSet));
        }

        return fuelEngineList;
    }

    public static FuelEngine selectFuelEngineById(int id) throws SQLException {
        FuelEngine fuelEngine = null;

        String query = String.format("SELECT * FROM %s WHERE %s=%d",
            FUEL_ENGINE_TABLE_NAME, FUEL_ENGINE_ID_COLUMN, id);
        ResultSet resultSet = connector.select(query);
        while( resultSet.next()) {
            fuelEngine = getFuelEngineFromResultSet(resultSet);
        }
        return fuelEngine;
    }

    public static int insertFuelEngine(FuelEngine fuelEngine) throws ValidationException {
        List<String> validationErrors = validateFuelEngine(fuelEngine);
        if(validationErrors.size() > 0){
            throw new ValidationException(validationErrors);
        }
        String query = String.format(
                "INSERT INTO `%s` (%s, %s, %s, %s, %s, %s, %s, %s, %s) " +
                "values('%s', %f, %d, %f, '%s', %b, %b, '%s',%f);",
                FUEL_ENGINE_TABLE_NAME, FUEL_ENGINE_FUEL_TYPE_COLUMN,
                FUEL_ENGINE_FUEL_CONSUMPTION_COLUMN, FUEL_ENGINE_NUMBER_OF_CYLINDERS_COLUMN,
                FUEL_ENGINE_CO2_EMISSIONS_COLUMN, FUEL_ENGINE_LAYOUT_COLUMN,
                FUEL_ENGINE_HAS_TURBINE_COLUMN, FUEL_ENGINE_HAS_SUPERCHARGE_COLUMN,
                FUEL_ENGINE_STROKE_TYPE_COLUMN,FUEL_ENGINE_DISPLACEMENT_COLUMN,
                fuelEngine.getFuelType(), fuelEngine.getFuelConsumption(),
                fuelEngine.getNumberOfCylinders(), fuelEngine.getCO2Emissions(),
                fuelEngine.getEngineLayout(), fuelEngine.isHasTurbine(),
                fuelEngine.isHasSuperCharger(), fuelEngine.getStrokeType(),
                fuelEngine.getDisplacement());

        return connector.execute(query);
    }

    public static int updateFuelEngine(FuelEngine newFuelEngine) throws ValidationException {
        List<String> validationErrors = validateFuelEngine(newFuelEngine);
        if(validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        String query = String.format("UPDATE `%s` SET " +
                "%s = '%s', %s = %f, %s = %d, " +
                "%s = %f, %s = '%s', %s = %b, " +
                "%s = %b, %s = '%s', %s = %f" +
                "WHERE %s = %d;",
                FUEL_ENGINE_TABLE_NAME,
                FUEL_ENGINE_FUEL_TYPE_COLUMN, newFuelEngine.getFuelType(),
                FUEL_ENGINE_FUEL_CONSUMPTION_COLUMN, newFuelEngine.getFuelConsumption(),
                FUEL_ENGINE_NUMBER_OF_CYLINDERS_COLUMN, newFuelEngine.getNumberOfCylinders(),
                FUEL_ENGINE_CO2_EMISSIONS_COLUMN, newFuelEngine.getCO2Emissions(),
                FUEL_ENGINE_LAYOUT_COLUMN, newFuelEngine.getEngineLayout(),
                FUEL_ENGINE_HAS_TURBINE_COLUMN, newFuelEngine.isHasTurbine(),
                FUEL_ENGINE_HAS_SUPERCHARGE_COLUMN, newFuelEngine.isHasSuperCharger(),
                FUEL_ENGINE_STROKE_TYPE_COLUMN, newFuelEngine.getStrokeType(),
                FUEL_ENGINE_DISPLACEMENT_COLUMN, newFuelEngine.getDisplacement(),
                FUEL_ENGINE_ID_COLUMN, newFuelEngine.getId());

        return connector.execute(query);
    }

    public static int deleteFuelEngine(int id){
        String query = String.format("DELETE FROM %s WHERE %s=%d",
                FUEL_ENGINE_TABLE_NAME, FUEL_ENGINE_ID_COLUMN, id);
        return connector.execute(query);
    }

    public static FuelEngine getFuelEngineFromResultSet(ResultSet resultSet) throws SQLException {
        return new FuelEngine(
                resultSet.getInt(FUEL_ENGINE_ID_COLUMN),
                FuelType.valueOf(resultSet.getString(FUEL_ENGINE_FUEL_TYPE_COLUMN)),
                resultSet.getFloat(FUEL_ENGINE_FUEL_CONSUMPTION_COLUMN),
                resultSet.getInt(FUEL_ENGINE_NUMBER_OF_CYLINDERS_COLUMN),
                resultSet.getFloat(FUEL_ENGINE_CO2_EMISSIONS_COLUMN),
                EngineLayout.valueOf(resultSet.getString(FUEL_ENGINE_LAYOUT_COLUMN)),
                resultSet.getBoolean(FUEL_ENGINE_HAS_TURBINE_COLUMN),
                resultSet.getBoolean(FUEL_ENGINE_HAS_SUPERCHARGE_COLUMN),
                StrokeType.valueOf(resultSet.getString(FUEL_ENGINE_STROKE_TYPE_COLUMN)),
                resultSet.getFloat(FUEL_ENGINE_DISPLACEMENT_COLUMN));
    }

    public static List<String> validateFuelEngine(FuelEngine fuelEngine){
        List<String> validationErrors = new ArrayList<>();

        if(fuelEngine.getFuelConsumption() <= 0){
            validationErrors.add("Fuel consumption can't be lower than zero");
        }
        if(fuelEngine.getFuelConsumption() > 40){
            validationErrors.add("Fuel consumption must be below 40");
        }

        if(fuelEngine.getNumberOfCylinders() <= 0){
            validationErrors.add("Number of cylinders can't be lower than zero");
        }
        if(fuelEngine.getNumberOfCylinders() > 16){
            validationErrors.add("Number of cylinders must be 16 or less");
        }

        if(fuelEngine.getCO2Emissions() <= 0){
            validationErrors.add("CO2 emissions can't be lower than zero");
        }
        if(fuelEngine.getCO2Emissions() > 250){
            validationErrors.add("CO2 emissions can't be grater than 250");
        }

        if(fuelEngine.getDisplacement() <= 0){
            validationErrors.add("Displacement can't be lower than zero");
        }
        if(fuelEngine.getDisplacement() > 9){
            validationErrors.add("Displacement must be lower than 9.0");
        }

        return validationErrors;
    }

}
