package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.ElectricEngine;
import com.crystal.ovs.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudElectricEngineTests {

    private List<String> getLinesOfResultSet(ResultSet resultSet) throws SQLException {
        // this method gets all rows selected from the database and converts them into strings where column values are separated by: ','
        // adds every converted row one by one into a list
        // finally returns the list
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        List<String> selectedObjects = new ArrayList<>();
        int numberOfColumns = rsMetaData.getColumnCount();
        while (resultSet.next()) {
            StringBuilder result = new StringBuilder();
            for (int i = 1; i <= numberOfColumns; i++) {
                String columnValue = resultSet.getString(i);
                result.append(columnValue).append(",");
            }
            result.deleteCharAt(result.length() - 1);
            selectedObjects.add(result.toString());
        }
        return selectedObjects;
    }

    @Test
    public void getNumberOfRowsTest() { // works
        int expectedNumberOfRows = 4;
        int numberOfRows = CrudElectricEngine.getNumberOfRows();
        Assert.assertEquals(expectedNumberOfRows, numberOfRows);
    }

    @Test
    public void selectAllElectricEngineTest() throws SQLException { // works
        ResultSet resultSet = CrudElectricEngine.selectAll();
        List<String> result = getLinesOfResultSet(resultSet);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void insertAllElectricEngineTest() throws ValidationException { // works
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        ElectricEngine electricEngine = new ElectricEngine(1, "someType", 6000, 450);
        CrudElectricEngine.insertEngine(electricEngine);
        Assert.assertEquals(initialRowNumber + 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void deleteFromElectricEngineByIdTest() { // works
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        int id = 6;
        CrudElectricEngine.deleteById(id);
        Assert.assertEquals(initialRowNumber - 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() throws SQLException, ValidationException { // works
        int id = 1;
        ElectricEngine electricEngine = new ElectricEngine(id, "tipUpdate", 2000, 1234);
        CrudElectricEngine.updateAllById(electricEngine);
        ResultSet resultSet = CrudElectricEngine.selectAll();
        List<String> result = getLinesOfResultSet(resultSet);

        String expectedResult = "1,tipUpdate,2000,1234";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateEngineTypeByIdTest() throws SQLException { // works
        int id = 1;
        CrudElectricEngine.updateEngineTypeById(id, "altType");

        ResultSet resultSet = CrudElectricEngine.selectAll();
        List<String> result = getLinesOfResultSet(resultSet);

        String expectedResult = "1,altType,2000,1234";
        Assert.assertEquals(expectedResult, result.get(0));
    }


    @Test
    public void updateBatteryCapacityByIdTest() throws SQLException { // works
        int id = 1;
        CrudElectricEngine.updateBatteryCapacityById(id, 3000);

        ResultSet resultSet = CrudElectricEngine.selectAll();
        List<String> result = getLinesOfResultSet(resultSet);
        String expectedResult = "1,altType,3000,1234";
        Assert.assertEquals(expectedResult, result.get(0));
    }


    @Test
    public void updateEngineRangeByIdTest() throws SQLException { // works
        int id = 1;
        CrudElectricEngine.updateEngineRangeById(id, 4000);

        ResultSet resultSet = CrudElectricEngine.selectAll();
        List<String> result = getLinesOfResultSet(resultSet);

        String expectedResult = "1,altType,3000,4000";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void selectAllElectricEnginesAndReturnList() throws SQLException {
        List<ElectricEngine> actual = CrudElectricEngine.getAllElectricEngines();
        Assert.assertEquals(CrudElectricEngine.getNumberOfRows(), actual.size());
    }
}
