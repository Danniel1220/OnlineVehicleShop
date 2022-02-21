package com.crystal.ovs.database.crud;

import org.junit.Assert;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudCarTests {
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
        int expectedNumberOfRows = 0;
        int numberOfRows = CrudCar.getNumberOfRows();
        Assert.assertEquals(expectedNumberOfRows, numberOfRows);
    }

    @Test
    public void deleteFromCarByIdTest() { // works
        int initialRowNumber = CrudCar.getNumberOfRows();
        int id = 3;
        CrudCar.deleteById(id);
        Assert.assertEquals(initialRowNumber - 1, CrudCar.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarBrandByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarModelByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarVINByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarManufacturingYearByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarTypeByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarEngineIdByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarTransmissionIdByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarTractionTypeByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarNumberOfDoorsByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }

    @Test
    public void updateCarColorByIdTest() {
        // NEED SELECT METHOD IN ORDER TO TEST
    }
}
