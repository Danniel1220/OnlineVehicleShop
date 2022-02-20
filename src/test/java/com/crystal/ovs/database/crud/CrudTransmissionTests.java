package com.crystal.ovs.database.crud;

import com.crystal.ovs.crud.CrudTransmission;
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
        CrudTransmission.insertTransmissionTable("MANUAL",5);
        Assert.assertEquals(initialRowNumber + 1, CrudTransmission.getNumberOfRows());
    }

    @Test
    public void deleteTransmissionTableTest() throws SQLException {
        int initialRowNumber = CrudTransmission.getNumberOfRows();
        CrudTransmission.deleteTransmissionTable(2);
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
        CrudTransmission.updateTransmissionTableInt("numberOfGears",id,5);
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
        String expectedResult = "1 AUTOMATA 5 ";
        Assert.assertEquals(expectedResult, result.toString());
    }

    @Test
    public void updateStringColumnByIdTest() throws SQLException {
        int id = 1;
        CrudTransmission.updateTransmissionTableString("transmissionType",id,"MANUAL");
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
        String expectedResult = "1 MANUAL 5 ";
        Assert.assertEquals(expectedResult, result.toString());
    }

    @Test
    public void updateAllById() throws SQLException {
        int id = 1;
        CrudTransmission.updateAllById(id,"AUTOMATA",10);
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
        String expectedResult = "1 AUTOMATA 10 ";
        Assert.assertEquals(expectedResult, result.toString());
    }
}
