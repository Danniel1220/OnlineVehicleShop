package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Transmission;
import com.crystal.ovs.dao.TransmissionType;
import com.crystal.ovs.database.crud.CrudTransmission;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CrudTransmissionTests {
    @Test
    public void getNumberOfRowsTest() {
        int numberOfRows = CrudTransmission.getNumberOfRows();
        Assert.assertEquals(1, numberOfRows);
    }

    @Test
    public void selectAllFromTransmissionTest() throws Exception {
        ResultSet resultSet = CrudTransmission.selectAllFromTransmissionTable();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        StringBuilder result = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                result.append(columnValue).append(" ");
            }
        }
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
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        StringBuilder result = new StringBuilder();
        if (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                result.append(columnValue).append(" ");
            }
        }
        String expectedResult = "5 AUTOMATIC 10 ";
        Assert.assertEquals(expectedResult, result.toString());
    }

    @Test
    public void updateStringColumnByIdTest() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.MANUAL,5);
        CrudTransmission.updateTransmissionTableString(transmission);
        ResultSet resultSet = CrudTransmission.selectAllFromTransmissionTable();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        StringBuilder result = new StringBuilder();
        if (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                result.append(columnValue).append(" ");
            }
        }
        String expectedResult = "5 MANUAL 10 ";
        Assert.assertEquals(expectedResult, result.toString());
    }

    @Test
    public void updateAllById() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.DUALCLUTCH,15);
        CrudTransmission.updateAllById(transmission);
        ResultSet resultSet = CrudTransmission.selectAllFromTransmissionTable();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        StringBuilder result = new StringBuilder();
        if (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                result.append(columnValue).append(" ");
            }
        }
        String expectedResult = "5 DUALCLUTCH 15 ";
        Assert.assertEquals(expectedResult, result.toString());
    }
}
