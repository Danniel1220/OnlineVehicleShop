package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Car;
import com.crystal.ovs.dao.CarType;
import com.crystal.ovs.dao.TractionType;
import org.junit.Assert;
import org.junit.Test;
import java.awt.*;
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
    public void selectAllFromCarTest() throws Exception { // works
        ResultSet resultSet = CrudCar.selectAllFromCar();
        List<String> result = getLinesOfResultSet(resultSet);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectListOfColumnsFromCarTest() throws Exception { // works
        ArrayList<String> columnlist = new ArrayList<>();
        columnlist.add("brand");
        columnlist.add("model");
        columnlist.add("carType");
        ResultSet resultSet = CrudCar.selectListOfColumnsFromCar(columnlist);
        List<String> result = getLinesOfResultSet(resultSet);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test // gives a foreign key constraint fail on engine id TODO fix insert for car
    public void insertIntoCarTest(){
        int initialRowNumber = CrudCar.getNumberOfRows();
        Car car = new Car(2,"Ford", "Mustang", "5sd5a6f58ds9f7s8", 2019, CarType.SPORTS,1,
                6, TractionType.RWD, 2, Color.CYAN);
        CrudCar.insertIntoCar(car);
        Assert.assertEquals(initialRowNumber + 1, CrudCar.getNumberOfRows());
    }

    @Test
    public void deleteFromCarByIdTest() { // works
        int initialRowNumber = CrudCar.getNumberOfRows();
        int id = 3;
        CrudCar.deleteById(id);
        Assert.assertEquals(initialRowNumber - 1, CrudCar.getNumberOfRows());
    }

    @Test
    public void updateAllByIdTest() throws SQLException { // works
        Car car = new Car(5, "BMW", "X5", "123TDF", 2019, CarType.SPORTS, 1, 2, TractionType.AWD, 4, Color.BLUE);
        CrudCar.updateAllById(car);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,BMW,X5,123TDF,2019,SPORTS,1,2,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarBrandByIdTest() throws SQLException { // works
        CrudCar.updateCarBrandById(5, "Renault");
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,X5,123TDF,2019,SPORTS,1,2,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarModelByIdTest() throws SQLException { // works
        CrudCar.updateCarModelById(5, "Symbol");
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,123TDF,2019,SPORTS,1,2,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarVINByIdTest() throws SQLException { // works
        CrudCar.updateCarVINById(5, "VIN123");
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2019,SPORTS,1,2,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarManufacturingYearByIdTest() throws SQLException { // works
        CrudCar.updateCarManufacturingYearById(5, 2021);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2021,SPORTS,1,2,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarTypeByIdTest() throws SQLException { // works
        CrudCar.updateCarTypeById(5, CarType.SUV);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2021,SUV,1,2,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarEngineIdByIdTest() throws SQLException { // works
        CrudCar.updateCarEngineIdById(5, 12);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2021,SUV,12,2,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarTransmissionIdByIdTest() throws SQLException { // works
        CrudCar.updateCarTransmissionIdById(5, 1);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2021,SUV,12,1,AWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarTractionTypeByIdTest() throws SQLException { // works
        CrudCar.updateCarTractionTypeById(5, TractionType.RWD);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2021,SUV,12,1,RWD,4,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarNumberOfDoorsByIdTest() throws SQLException { // works
        CrudCar.updateCarNumberOfDoorsById(5, 2);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2021,SUV,12,1,RWD,2,java.awt.Color[r=0,g=0,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateCarColorByIdTest() throws SQLException { // works
        CrudCar.updateCarColorById(5, Color.CYAN);
        List<String> result = getLinesOfResultSet(CrudCar.selectAllFromCar());
        String expectedResult = "5,Renault,Symbol,VIN123,2021,SUV,12,1,RWD,2,java.awt.Color[r=0,g=255,b=255]";
        Assert.assertEquals(expectedResult, result.get(0));
    }
}
