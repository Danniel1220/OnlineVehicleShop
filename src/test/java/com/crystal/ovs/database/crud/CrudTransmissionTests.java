package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Post;
import com.crystal.ovs.dao.Transmission;
import com.crystal.ovs.dao.TransmissionType;
import com.crystal.ovs.database.DatabaseConnector;
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
        Assert.assertEquals(5, numberOfRows);
    }

    @Test
    public void selectAllFromTransmissionTest() throws Exception {
        List<Transmission> transmissionList;
        transmissionList = CrudTransmission.selectAllFromTransmissionTable();
        int nrOfRows = CrudTransmission.getNumberOfRows();
        Assert.assertEquals(nrOfRows, transmissionList.size());
    }

    @Test
    public void selectTransmissionByIDTest() throws Exception {
        DatabaseConnector databaseConnector;
        databaseConnector = DatabaseConnector.getInstance();
        ResultSet resultSet = databaseConnector.select(String.format("select * from transmission where id = %d", 5));

        try {
            Assert.assertTrue(resultSet.next());

            Transmission expectedTransmission = CrudTransmission.getTransmissionFromResultSet(resultSet);
            Transmission transmission = CrudTransmission.selectAllByTransmissionId(5);

            Assert.assertEquals(expectedTransmission, transmission);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void updateNrOfGearsColumnByIdTest() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.DUALCLUTCH,10);
        CrudTransmission.updateTransmissionTableNrOfGears(transmission);
        Transmission actualTransmission;
        actualTransmission = CrudTransmission.selectAllByTransmissionId(5);

        Assert.assertEquals(transmission, actualTransmission);
    }

    @Test
    public void updateTransmissionTypeColumnByIdTest() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.MANUAL,10);
        CrudTransmission.updateTransmissionTableTransmissionType(transmission);
        Transmission actualTransmission;
        actualTransmission = CrudTransmission.selectAllByTransmissionId(5);

        Assert.assertEquals(transmission, actualTransmission);
    }

    @Test
    public void updateAllByIdTest() throws SQLException {
        int id = 1;
        Transmission transmission = new Transmission(5, TransmissionType.DUALCLUTCH,15);
        CrudTransmission.updateAllById(transmission);
        CrudTransmission.updateTransmissionTableTransmissionType(transmission);
        Transmission actualTransmission;
        actualTransmission = CrudTransmission.selectAllByTransmissionId(5);

        Assert.assertEquals(transmission, actualTransmission);
    }
}
