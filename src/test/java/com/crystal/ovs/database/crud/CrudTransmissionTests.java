package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Transmission;
import com.crystal.ovs.dao.TransmissionType;
import com.crystal.ovs.database.crud.CrudTransmission;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudTransmissionTests {

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
    public void getNumberOfRowsTest() {
        int numberOfRows = CrudTransmission.getNumberOfRows();
        Assert.assertEquals(1, numberOfRows);
    }

    @Test
    public void selectAllFromTransmissionTest() throws Exception {
        ResultSet resultSet = CrudTransmission.selectAllFromTransmissionTable();
        List<String> result = getLinesOfResultSet(resultSet);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void insertTransmissionTableTest() throws SQLException {
        int initialRowNumber = CrudTransmission.getNumberOfRows();
        Transmission transmission = new Transmission(1, TransmissionType.AUTOMATIC,5);
        CrudTransmission.insertTransmissionTable(transmission);
        Assert.assertEquals(initialRowNumber + 1, CrudTransmission.getNumberOfRows());
    }

    @Test
    public void deleteTransmissionTableTest() throws SQLException {
        int initialRowNumber = CrudTransmission.getNumberOfRows();
        CrudTransmission.deleteTransmissionTable(4);
        Assert.assertEquals(initialRowNumber - 1,CrudTransmission.getNumberOfRows());
    }

    /*@Test
    public void deleteAllRowsTest() throws SQLException {
        CrudTransmission.deleteAllRows();
        Assert.assertEquals(0,CrudTransmission.getNumberOfRows());
    }*/

    @Test
    public void updateIntColumnByIdTest() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.DUALCLUTCH,10);
        CrudTransmission.updateTransmissionTableInt(transmission);
        ResultSet resultSet = CrudTransmission.selectAllFromTransmissionTable();
        List<String> result = getLinesOfResultSet(resultSet);
        System.out.println(result);
        Assert.assertNotNull(result);
        String expectedResult = "5,DUALCLUTCH,10";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateStringColumnByIdTest() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.MANUAL,5);
        CrudTransmission.updateTransmissionTableString(transmission);
        ResultSet resultSet = CrudTransmission.selectAllFromTransmissionTable();
        List<String> result = getLinesOfResultSet(resultSet);
        System.out.println(result);
        Assert.assertNotNull(result);
        String expectedResult = "5,MANUAL,10";
        Assert.assertEquals(expectedResult, result.get(0));
    }

    @Test
    public void updateAllById() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.DUALCLUTCH,15);
        CrudTransmission.updateAllById(transmission);
        ResultSet resultSet = CrudTransmission.selectAllFromTransmissionTable();
        List<String> result = getLinesOfResultSet(resultSet);
        System.out.println(result);
        Assert.assertNotNull(result);
        String expectedResult = "5,DUALCLUTCH,15";
        Assert.assertEquals(expectedResult, result.get(0));
    }
}
