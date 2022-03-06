package com.crystal.ovs.database.crud;

import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.database.crud.CrudFuelEngine;
import com.crystal.ovs.dao.EngineLayout;
import com.crystal.ovs.dao.FuelEngine;
import com.crystal.ovs.dao.FuelType;
import com.crystal.ovs.dao.StrokeType;
import com.crystal.ovs.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CrudFuelEngineTest {
    static DatabaseConnector connector;

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
            List<FuelEngine> fuelEngineList = CrudFuelEngine.selectAllFuelEngines();

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

            FuelEngine expectedFuelEngine = CrudFuelEngine.getFuelEngineFromResultSet(resultSet);
            FuelEngine actualFuelEngine = CrudFuelEngine.selectFuelEngineById(EXISTING_ID);
            Assert.assertEquals(expectedFuelEngine, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnNullFuelEngine(){
       try {
            FuelEngine actualFuelEngine = CrudFuelEngine.selectFuelEngineById(NONEXISTENT_ID);
            Assert.assertEquals(null, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldInsertFuelEngineOnce(){
        int actualFuelEngineCount = 0;
        try {
            actualFuelEngineCount = CrudFuelEngine.insertFuelEngine(new FuelEngine(EXISTING_ID, FuelType.DIESEL,
                    12.1f, 10, 200.1f, EngineLayout.BOXER, true,
                    false, StrokeType.TWO_STROKE, 1.4f));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, actualFuelEngineCount);
    }

    @Test
    public void shouldThrowValidationErrorForCreate(){
        FuelEngine newFuelEngine = new FuelEngine(EXISTING_ID, FuelType.GASOLINE, 150f,
                80, 2000.2f, EngineLayout.VR, false, true,
                StrokeType.FOUR_STROKE, 10f);

        Assert.assertThrows(ValidationException.class, () -> {
            CrudFuelEngine.insertFuelEngine(newFuelEngine);
        });
    }

    @Test
    public void shouldUpdateOnlyOneFuelEngine(){
        FuelEngine newFuelEngine = new FuelEngine(EXISTING_ID, FuelType.GASOLINE, 15f,
                8, 200.2f, EngineLayout.VR, false, true,
                StrokeType.FOUR_STROKE, 1.9f);

        int actualAffectedRowsCount = 0;
        try {
            actualAffectedRowsCount = CrudFuelEngine.updateFuelEngine(newFuelEngine);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        int expectedAffectedRowsCount = 1;

        Assert.assertEquals(expectedAffectedRowsCount, actualAffectedRowsCount);

        try {
            FuelEngine actualFuelEngine = CrudFuelEngine.selectFuelEngineById(EXISTING_ID);
            Assert.assertEquals(newFuelEngine, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldUpdateNoFuelEngine(){
        FuelEngine newFuelEngine = new FuelEngine(NONEXISTENT_ID, FuelType.GASOLINE, 15f,
                8, 200.2f, EngineLayout.VR, false, true,
                StrokeType.FOUR_STROKE, 1.9f);

        int actualAffectedRowsCount = 0;
        try {
            actualAffectedRowsCount = CrudFuelEngine.updateFuelEngine(newFuelEngine);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        int expectedAffectedRowsCount = 0;

        Assert.assertEquals(expectedAffectedRowsCount, actualAffectedRowsCount);
    }

    @Test
    public void shouldThrowValidationErrorForUpdate(){
        FuelEngine newFuelEngine = new FuelEngine(EXISTING_ID, FuelType.GASOLINE, 150f,
                80, 2000.2f, EngineLayout.VR, false, true,
                StrokeType.FOUR_STROKE, 10f);

        Assert.assertThrows(ValidationException.class, () -> {
            CrudFuelEngine.updateFuelEngine(newFuelEngine);
        });
    }

    @Test
    public void shouldDeleteOnlyOneFuelEngine(){
        int actualDeletedRows = CrudFuelEngine.deleteFuelEngine(EXISTING_ID);
        int expectedDeletedRows = 1;

        Assert.assertEquals(expectedDeletedRows, actualDeletedRows);

        try {
            FuelEngine actualFuelEngine = CrudFuelEngine.selectFuelEngineById(EXISTING_ID);
            FuelEngine expectedFuelEngine = null;

            Assert.assertEquals(expectedFuelEngine, actualFuelEngine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldDeleteNoFuelEngine(){
        int actualDeletedRows = CrudFuelEngine.deleteFuelEngine(NONEXISTENT_ID);
        int expectedDeletedRows = 0;

        Assert.assertEquals(expectedDeletedRows, actualDeletedRows);

    }
}
