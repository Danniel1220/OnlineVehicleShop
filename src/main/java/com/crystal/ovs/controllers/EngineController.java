package com.crystal.ovs.controllers;

import com.crystal.ovs.dao.*;

import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineController {
    private static DatabaseConnector connector;

    static {
        connector = DatabaseConnector.getInstance();
    }
    public static List<Engine> selectAllEngines() throws SQLException {
        List<Engine> engineList = new ArrayList<>();

        String query = "select * from engine;";
        ResultSet resultSet = connector.select(query);
        while(resultSet.next()){
            engineList.add(getEngineFromResultSet(resultSet));
        }

        return engineList;
    }
    public static int insertEngine(Engine engine) throws ValidationException {
        List<String> validationErrors = validateEngine(engine);
        if(validationErrors.size() > 0){
            throw new ValidationException(validationErrors);
        }
        String query = String.format(
                "insert into `engine` (horsePower, fuelEngineId,electricEngineId,torque) " +
                        "values( %d, %d, %d, %d  );",
                engine.getHorsePower(), engine.getFuelEngineId(),
                engine.getElectricEngineId(),engine.getTorque());

        return connector.execute(query);
    }
    public static Engine selectEngineById(int id) throws SQLException {
        Engine engine = null;

        String query = String.format("select * from engine where id=%d", id);
        ResultSet resultSet = connector.select(query);
        while( resultSet.next()) {
            engine = getEngineFromResultSet(resultSet);
        }
        return engine;
    }
    public static int deleteEngine(int id){
        String query = String.format("delete from engine where id=%d", id);
        return connector.execute(query);
    }
    public static Engine getEngineFromResultSet(ResultSet resultSet) throws SQLException {
        return new Engine(
                resultSet.getInt("id"),
                resultSet.getInt("torque"),
                resultSet.getInt("horsePower"),
                resultSet.getInt("fuelEngineId"),
                resultSet.getInt("electricEngineId"));
    }

    public static int updateEngine(Engine newEngine) throws ValidationException {
        List<String> validationErrors = validateEngine(newEngine);
        if(validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        String query = String.format("update `engine` set " +
                        "torque = %d, " +
                        "horsePower = %d, " +
                        "fuelEngineId = %d, " +
                        "electricEngineId = %d, " +
                        "where id = %d;",
                newEngine.getTorque(), newEngine.getHorsePower(),
                newEngine.getFuelEngineId(), newEngine.getElectricEngineId(),
                newEngine.getId());

        return connector.execute(query);
    }


    public static java.util.List<String> validateEngine(Engine engine){
        List<String> validationErrors = new ArrayList<>();

        if(engine.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(engine.getFuelEngineId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(engine.getElectricEngineId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(engine.getHorsePower() <= 60) {
            validationErrors.add("Can't have horse power < 60 ");
        }
        if(engine.getTorque() <= 0) {
            validationErrors.add("cannot be less than or equal to 0 ");
        }


        return validationErrors;
    }

}
