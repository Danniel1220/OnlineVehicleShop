package com.crystal.ovs.database.crud;

import com.crystal.ovs.database.crud.CrudCar;
import org.junit.Assert;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CrudCarTests {
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
