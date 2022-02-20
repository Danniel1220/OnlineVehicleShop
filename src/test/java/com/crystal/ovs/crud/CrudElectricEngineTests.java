package com.crystal.ovs.crud;

import com.crystal.ovs.dao.ElectricEngine;
import org.junit.Assert;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CrudElectricEngineTests {
    private String getFirstLineOfResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        StringBuilder result = new StringBuilder();
        if (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                result.append(columnValue).append(" ");
            }
        }
        return result.toString();
    }

    @Test
    public void getNumberOfRowsTest() {
        int expectedNumberOfRows = 3;
        int numberOfRows = CrudElectricEngine.getNumberOfRows();
        Assert.assertEquals(expectedNumberOfRows, numberOfRows);
    }

    @Test
    public void selectAllElectricEngineTest() throws SQLException {
        ResultSet resultSet = CrudElectricEngine.selectAll();
        String result = getFirstLineOfResultSet(resultSet);
        Assert.assertNotNull(result);
    }

    @Test
    public void insertAllElectricEngineTest() {
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        ElectricEngine electricEngine = new ElectricEngine(CrudElectricEngine.getNumberOfRows() + 1, "otherType", 100, 250);
        CrudElectricEngine.insertEngine(electricEngine);
        Assert.assertEquals(initialRowNumber + 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void deleteFromElectricEngineWhereTest() {
        int initialRowNumber = CrudElectricEngine.getNumberOfRows();
        int id = 4;
        CrudElectricEngine.deleteById(id);
        Assert.assertEquals(initialRowNumber - 1, CrudElectricEngine.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() throws SQLException {
        int id = 1;
        ElectricEngine electricEngine = new ElectricEngine(id, "tipUpdate", 2, 3);
        CrudElectricEngine.updateAllById(electricEngine);
        ResultSet resultSet = CrudElectricEngine.selectAll();
        String result = getFirstLineOfResultSet(resultSet);

        String expectedResult = "1 tipUpdate 2 3 ";
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void updateEngineTypeByIdTest() throws SQLException {
        int id = 1;
        CrudElectricEngine.updateEngineTypeById(id, "altType");

        ResultSet resultSet = CrudElectricEngine.selectAll();
        String result = getFirstLineOfResultSet(resultSet);

        String expectedResult = "1 altType 2 3 ";
        Assert.assertEquals(expectedResult, result);
    }


    @Test
    public void updateBatteryCapacityByIdTest() throws SQLException {
        int id = 1;
        CrudElectricEngine.updateBatteryCapacityById(id, 2000);

        ResultSet resultSet = CrudElectricEngine.selectAll();
        String result = getFirstLineOfResultSet(resultSet);

        String expectedResult = "1 altType 2000 3 ";
        Assert.assertEquals(expectedResult, result);
    }


    @Test
    public void updateEngineRangeByIdTest() throws SQLException {
        int id = 1;
        CrudElectricEngine.updateEngineRangeById(id, 1234);

        ResultSet resultSet = CrudElectricEngine.selectAll();
        String result = getFirstLineOfResultSet(resultSet);

        String expectedResult = "1 altType 2000 1234 ";
        Assert.assertEquals(expectedResult, result);
    }
}
