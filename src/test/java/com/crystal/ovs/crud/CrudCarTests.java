package com.crystal.ovs.crud;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.CarType;
import com.crystal.ovs.dao.TractionType;
import org.junit.Assert;
import org.junit.Test;
import java.awt.*;
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
    public void getNumberOfRowsTest() {
        int expectedNumberOfRows = 0;
        int numberOfRows = CrudCar.getNumberOfRows();
        Assert.assertEquals(expectedNumberOfRows, numberOfRows);
    }

    @Test
    public void deleteFromCarByIdTest() {
        int initialRowNumber = CrudCar.getNumberOfRows();
        int id = 2;
        CrudCar.deleteById(id);
        Assert.assertEquals(initialRowNumber - 1, CrudCar.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() throws SQLException {
        int id = 3;
        Car car = new Car(id, "BMW", "X5", "123TDF", 2019, CarType.SUV, 1, 2, TractionType.AWD, 4, Color.BLUE);
        CrudCar.updateAllById(car);
        // ResultSet resultSet = CrudCar.select;
       //  String result = getFirstLineOfResultSet(resultSet);

        String expectedResult = "3 BMW X5 123TDF 2019 SUV 1 2 AWD 4 BLUE ";
      //  Assert.assertEquals(expectedResult, result);
    }
}
