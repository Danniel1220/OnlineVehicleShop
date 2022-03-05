package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.ElectricEngine;
import org.junit.Assert;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

/**
 * Tests for all CrudElectricEngine methods
 */

public class CrudElectricEngineTests {

    @Test
    public void getNumberOfRowsTest() { // works
        int expectedNumberOfRows = 4;
        int numberOfRows = CrudElectricEngine.getNumberOfRows();
        Assert.assertEquals(expectedNumberOfRows, numberOfRows);
    }

    @Test
    public void selectAllElectricEngineTest() throws SQLException { // works
        List<ElectricEngine> electricEngines = CrudElectricEngine.selectAllElectricEngines();
        assert electricEngines != null;
        for(ElectricEngine electricEngine : electricEngines) {
            System.out.println(electricEngine.toString());
        }
        Assert.assertNotNull(electricEngines);
    }

    @Test
    public void insertElectricEngineTest() { // works
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        ElectricEngine electricEngine = new ElectricEngine(1, "someType", 6000, 450);
        CrudElectricEngine.insertEngine(electricEngine);
        Assert.assertEquals(initialRowNumber + 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void deleteFromElectricEngineByIdTest() { // works
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        int id = 5;
        CrudElectricEngine.deleteById(id);
        Assert.assertEquals(initialRowNumber - 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() throws SQLException { // works
        int id = 1;
        ElectricEngine electricEngine = new ElectricEngine(id, "tipUpdate", 2000, 1234);
        CrudElectricEngine.updateAllById(electricEngine);
        List<ElectricEngine> electricEngines = CrudElectricEngine.selectAllElectricEngines();

        String expectedResult = "1,tipUpdate,2000,1234";
        Assert.assertEquals(expectedResult, electricEngines.get(0));
    }

    @Test
    public void updateEngineTypeByIdTest() throws SQLException { // works
        int id = 1;
        CrudElectricEngine.updateEngineTypeById(id, "altType");

        List<ElectricEngine> electricEngines = CrudElectricEngine.selectAllElectricEngines();

        String expectedResult = "1,altType,2000,1234";
        Assert.assertEquals(expectedResult, electricEngines.get(0));
    }


    @Test
    public void updateBatteryCapacityByIdTest() throws SQLException { // works
        int id = 1;
        CrudElectricEngine.updateBatteryCapacityById(id, 3000);

        List<ElectricEngine> electricEngines = CrudElectricEngine.selectAllElectricEngines();
        String expectedResult = "1,altType,3000,1234";
        Assert.assertEquals(expectedResult, electricEngines.get(0));
    }


    @Test
    public void updateEngineRangeByIdTest() throws SQLException { // works
        int id = 1;
        CrudElectricEngine.updateEngineRangeById(id, 4000);

        List<ElectricEngine> electricEngines = CrudElectricEngine.selectAllElectricEngines();

        String expectedResult = "1,altType,3000,4000";
        Assert.assertEquals(expectedResult, electricEngines.get(0));
    }
}
