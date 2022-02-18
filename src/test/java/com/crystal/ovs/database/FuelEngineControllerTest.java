package com.crystal.ovs.database;

import com.crystal.ovs.controllers.FuelEngineController;
import com.crystal.ovs.dao.EngineLayout;
import com.crystal.ovs.dao.FuelEngine;
import com.crystal.ovs.dao.FuelType;
import com.crystal.ovs.dao.StrokeType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuelEngineControllerTest {
    static DatabaseConnector  connector;

    final static int EXISTING_ID = -1;
    final static int NONEXISTENT_ID = -2;

    @BeforeClass
    public static void initialize(){
        connector = DatabaseConnector.getInstance();

        FuelEngine fuelEngine = new FuelEngine(EXISTING_ID, FuelType.GASOLINE, 0f,
                0, 0f, EngineLayout.INLINE, false, false,
                StrokeType.TWO_STROKE, 0f);
        String query = String.format(
                "insert into `fuelengine` values(%d, '%s', %f, %d, %f, '%s', %b, %b, '%s',%f);",
                EXISTING_ID, FuelType.GASOLINE, 0f, 0, 0f, EngineLayout.INLINE, false, false, StrokeType.TWO_STROKE, 0f);

        connector.execute(query);
    }

    @Test
    public void shouldReturnAllFuelEngines(){
        try {
            List<FuelEngine> fuelEngineList = FuelEngineController.selectAllFuelEngines();

            ResultSet rs = connector.select("select count(id) as count from fuelengine;");
            rs.next();
            int count = rs.getInt("count");
            Assert.assertEquals(count, fuelEngineList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnFuelEngineWithSpecifiedId(){
        ResultSet resultSet = connector.select(String.format("select * from fuelengine where id = %d", EXISTING_ID));

        try {
            Assert.assertTrue(resultSet.next());

            FuelEngine expectedFuelEngine = FuelEngineController.selectFuelEngineFromResultSet(resultSet);
            FuelEngine actualFuelEngine = FuelEngineController.selectFuelEngineById(EXISTING_ID);
            Assert.assertEquals(expectedFuelEngine, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnNullFuelEngine(){
       try {
            FuelEngine actualFuelEngine = FuelEngineController.selectFuelEngineById(NONEXISTENT_ID);
            Assert.assertEquals(null, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldInsertFuelEngineOnce(){
        int actualFuelEngineCount = FuelEngineController.insertFuelEngine(new FuelEngine(EXISTING_ID, FuelType.DIESEL, 12.1f,
                10, 500.1f, EngineLayout.BOXER, true, false,
                StrokeType.TWO_STROKE, 1.4f));
        Assert.assertEquals(1, actualFuelEngineCount);
    }

    @Test
    public void shouldUpdateOnlyOneFuelEngine(){
        FuelEngine newFuelEngine = new FuelEngine(EXISTING_ID, FuelType.GASOLINE, 15f,
                8, 485.2f, EngineLayout.VR, false, true,
                StrokeType.FOUR_STROKE, 1.9f);

        int actualAffectedRowsCount = FuelEngineController.updateFuelEngine(newFuelEngine);
        int expectedAffectedRowsCount = 1;

        Assert.assertEquals(expectedAffectedRowsCount, actualAffectedRowsCount);

        try {
            FuelEngine actualFuelEngine = FuelEngineController.selectFuelEngineById(EXISTING_ID);
            Assert.assertEquals(newFuelEngine, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldUpdateNoFuelEngine(){
        FuelEngine newFuelEngine = new FuelEngine(NONEXISTENT_ID, FuelType.GASOLINE, 15f,
                8, 485.2f, EngineLayout.VR, false, true,
                StrokeType.FOUR_STROKE, 1.9f);

        int actualAffectedRowsCount = FuelEngineController.updateFuelEngine(newFuelEngine);
        int expectedAffectedRowsCount = 0;

        Assert.assertEquals(expectedAffectedRowsCount, actualAffectedRowsCount);
    }

    @Test
    public void shouldDeleteOnlyOneFuelEngine(){
        int actualDeletedRows = FuelEngineController.deleteFuelEngine(EXISTING_ID);
        int expectedDeletedRows = 1;

        Assert.assertEquals(expectedDeletedRows, actualDeletedRows);

        try {
            FuelEngine actualFuelEngine = FuelEngineController.selectFuelEngineById(EXISTING_ID);
            FuelEngine expectedFuelEngine = null;

            Assert.assertEquals(expectedFuelEngine, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldDeleteNoFuelEngine(){
        int actualDeletedRows = FuelEngineController.deleteFuelEngine(NONEXISTENT_ID);
        int expectedDeletedRows = 0;

        Assert.assertEquals(expectedDeletedRows, actualDeletedRows);

    }
}
