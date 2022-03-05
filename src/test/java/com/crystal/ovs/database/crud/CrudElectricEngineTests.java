package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.ElectricEngine;
import org.junit.Assert;
import org.junit.Test;
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
    public void selectAllElectricEngineTest() { // works
        List<ElectricEngine> electricEngines = CrudElectricEngine.selectAllElectricEngines();
        assert electricEngines != null;
        for(ElectricEngine electricEngine : electricEngines) {
            System.out.println(electricEngine.toString());
        }
        Assert.assertNotNull(electricEngines);
    }

    @Test
    public void selectElectricEngineByIdTest() { // works
        ElectricEngine electricEngine = CrudElectricEngine.selectElectricEngineById(1);
        ElectricEngine expectedElectricEngine = new ElectricEngine(1, "altType", 3000, 4000);
        Assert.assertEquals(expectedElectricEngine, electricEngine);

        electricEngine = CrudElectricEngine.selectElectricEngineById(2);
        expectedElectricEngine = new ElectricEngine(2, "anotherType", 100, 250);
        Assert.assertEquals(expectedElectricEngine, electricEngine);
    }

    @Test
    public void insertElectricEngineTest() { // works
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        ElectricEngine electricEngine = new ElectricEngine(1, "someType", 6000, 450);
        CrudElectricEngine.insertElectricEngine(electricEngine);
        Assert.assertEquals(initialRowNumber + 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void deleteFromElectricEngineByIdTest() { // works
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        int id = 5;
        CrudElectricEngine.deleteById(id);
        ElectricEngine electricEngine = CrudElectricEngine.selectElectricEngineById(5);
        Assert.assertNull(electricEngine);
        Assert.assertEquals(initialRowNumber - 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() { // works
        int id = 1;
        ElectricEngine electricEngine = new ElectricEngine(id, "altType", 200, 150);
        CrudElectricEngine.updateAllById(electricEngine);
        ElectricEngine actualElectricEngine = CrudElectricEngine.selectElectricEngineById(1);
        Assert.assertEquals(electricEngine, actualElectricEngine);
    }

    @Test
    public void updateEngineTypeByIdTest() { // works
        int id = 1;
        CrudElectricEngine.updateEngineTypeById(id, "something");
        ElectricEngine actualElectricEngine = CrudElectricEngine.selectElectricEngineById(1);
        String expectedType = "something";
        Assert.assertNotNull(actualElectricEngine);
        Assert.assertEquals(expectedType, actualElectricEngine.getType());
    }

    @Test
    public void updateBatteryCapacityByIdTest() { // works
        int id = 1;
        CrudElectricEngine.updateBatteryCapacityById(id, 250);
        ElectricEngine actualElectricEngine = CrudElectricEngine.selectElectricEngineById(1);
        Integer expectedBatteryCapacity = 250;
        Assert.assertNotNull(actualElectricEngine);
        Assert.assertEquals(expectedBatteryCapacity, actualElectricEngine.getBatteryCapacity());
    }

    @Test
    public void updateEngineRangeByIdTest() { // works
        int id = 1;
        CrudElectricEngine.updateEngineRangeById(id, 4000);
        ElectricEngine actualElectricEngine = CrudElectricEngine.selectElectricEngineById(1);
        Integer expectedEngineRange = 4000;
        Assert.assertNotNull(actualElectricEngine);
        Assert.assertEquals(expectedEngineRange, actualElectricEngine.getRange());
    }
}
