package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Engine;
import com.crystal.ovs.exceptions.ValidationException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class CrudEngineTest extends TestCase {

    public void testGetNumberOfRows() {
        int expectedNumberOfRows = 2;
        int numberOfRows = CrudEngine.getNumberOfRows();
        Assert.assertEquals(expectedNumberOfRows, numberOfRows);
    }

    @Test

    public void testSelectAllEngine() {
        List<Engine> existingEngine = CrudEngine.selectAllEngine();
        Assert.assertNotNull(existingEngine);
        Assert.assertNotEquals(0, existingEngine.size());
        Assert.assertEquals(Engine.class, existingEngine.get(0).getClass());
    }

    @Test
    public void testInsertEngine() throws ValidationException {
        int initialRowNumber = CrudEngine.getNumberOfRows();
        Engine engine = new Engine(3, 2, 3, 213, 421);
        CrudEngine.insertEngine(engine);
        Assert.assertEquals(initialRowNumber + 1, CrudEngine.getNumberOfRows());
    }

    @Test
    public void testUpdateAllEngineById() {
        Engine engine = new Engine(2, 2, 3, 421, 321);
        CrudEngine.updateAllEngineById(engine);
        List<Engine> existingEngine = CrudEngine.selectAllEngine();
        Assert.assertNotEquals(engine, Objects.requireNonNull(existingEngine).get(0));
    }

    @Test
    public void testUpdateElectricEngineIdFromEngine() {
        CrudEngine.updateEngineElectricById(2, 3);
        Engine engine = new Engine(2, 3, 6, 421, 321);
        List<Engine> existingEngine = CrudEngine.selectAllEngine();
        Assert.assertNotEquals(engine, Objects.requireNonNull(existingEngine).get(0));
    }
    @Test
    public void updateEngineHorsePowerById() { // works
        CrudEngine.updateEngineHorsePowerById(3, 213);
        Engine engine = new Engine(3, 2, 3, 2134, 421);
        List<Engine> existingEngine = CrudEngine.selectAllEngine();
        Assert.assertNotEquals(engine, Objects.requireNonNull(existingEngine).get(0));
    }

    @Test
    public void testDeleteById() {
        int initialRowNumber = CrudEngine.getNumberOfRows();
        int id = 3;
        CrudEngine.deleteEngine(id);
        Assert.assertEquals(initialRowNumber - 1, CrudEngine.getNumberOfRows());
    }


}