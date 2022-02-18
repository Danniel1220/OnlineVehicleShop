package com.crystal.ovs.controllers;

import com.crystal.ovs.dao.EngineLayout;
import com.crystal.ovs.dao.FuelEngine;
import com.crystal.ovs.dao.FuelType;
import com.crystal.ovs.dao.StrokeType;
import com.crystal.ovs.database.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuelEngineController {
    private static DatabaseConnector connector;

    static {
        connector = DatabaseConnector.getInstance();
    }

    public static List<FuelEngine> selectAllFuelEngines() throws SQLException {
        List<FuelEngine> fuelEngineList = new ArrayList<>();

        String query = "select * from fuelengine;";
        ResultSet resultSet = connector.select(query);
        while(resultSet.next()){
            fuelEngineList.add(selectFuelEngineFromResultSet(resultSet));
        }

        return fuelEngineList;
    }

    public static FuelEngine selectFuelEngineById(int id) throws SQLException {
        FuelEngine fuelEngine = null;

        String query = String.format("select * from fuelengine where id=%d", id);
        ResultSet resultSet = connector.select(query);
        while( resultSet.next()) {
            fuelEngine = selectFuelEngineFromResultSet(resultSet);
        }
        return fuelEngine;
    }

    public static int insertFuelEngine(FuelEngine fuelEngine){
        String query = String.format(
                "insert into `fuelengine` (fuelType, fuelConsumption, numberOfCylinders, CO2Emissions, engineLayout, hasTurbine, hasSuperCharger, strokeType, displacement) " +
                "values('%s', %f, %d, %f, '%s', %b, %b, '%s',%f);",
                fuelEngine.getFuelType(), fuelEngine.getFuelConsumption(), fuelEngine.getNumberOfCylinders(), fuelEngine.getCO2Emissions(),
                fuelEngine.getEngineLayout(), fuelEngine.isHasTurbine(), fuelEngine.isHasSuperCharger(), fuelEngine.getStrokeType(),
                fuelEngine.getDisplacement());

        return connector.execute(query);
    }

    public static int updateFuelEngine(FuelEngine newFuelEngine){
        String query = String.format("update `fuelengine` set " +
                "fuelType = '%s', " +
                "fuelConsumption = %f, " +
                "numberOfCylinders = %d, " +
                "CO2Emissions = %f, " +
                "engineLayout = '%s', " +
                "hasTurbine = %b, " +
                "hasSuperCharger = %b, " +
                "strokeType = '%s', " +
                "displacement = %f" +
                "where id = %d;",
                newFuelEngine.getFuelType(), newFuelEngine.getFuelConsumption(),
                newFuelEngine.getNumberOfCylinders(), newFuelEngine.getCO2Emissions(),
                newFuelEngine.getEngineLayout(), newFuelEngine.isHasTurbine(),
                newFuelEngine.isHasSuperCharger(), newFuelEngine.getStrokeType(),
                newFuelEngine.getDisplacement(), newFuelEngine.getId());

        return connector.execute(query);
    }

    public static int deleteFuelEngine(int id){
        String query = String.format("delete from fuelengine where id=%d", id);
        return connector.execute(query);
    }

    public static FuelEngine selectFuelEngineFromResultSet(ResultSet resultSet) throws SQLException {
        return new FuelEngine(
                resultSet.getInt("id"),
                FuelType.valueOf(resultSet.getString("fuelType")),
                resultSet.getFloat("fuelConsumption"),
                resultSet.getInt("numberOfCylinders"),
                resultSet.getFloat("CO2Emissions"),
                EngineLayout.valueOf(resultSet.getString("engineLayout")),
                resultSet.getBoolean("hasTurbine"),
                resultSet.getBoolean("hasSuperCharger"),
                StrokeType.valueOf(resultSet.getString("strokeType")),
                resultSet.getFloat("displacement"));
    }


}
