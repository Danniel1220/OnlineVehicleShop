package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.DatabaseConnector;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CrudTransactionTests {
    static DatabaseConnector connector;

    @BeforeClass
    public static void initialize(){
        connector = DatabaseConnector.getInstance();

        String query = "INSERT INTO `transaction` VALUES(-1, 1, 1, date('2022-01-01'));";
        connector.execute(query);
    }

    @Test
    public void shouldReturnAllTransactions(){
        try {
            List<Transaction> transactionList = CrudTransaction.selectAllUserTransactions(1);

            ResultSet rs = connector.select("SELECT COUNT(id) AS count FROM transaction WHERE idUser = 1;");
            rs.next();
            int count = rs.getInt("count");
            Assert.assertEquals(count, transactionList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldInsertTransactionOnce(){
        int actualFuelEngineCount = 0;
        actualFuelEngineCount = CrudTransaction.insertTransaction(
                new Transaction(0, 1, 1, new Date(2021, 01, 01)));
        Assert.assertEquals(1, actualFuelEngineCount);
    }

    @Test
    public void shouldUpdateOneTransaction(){
        int actualFuelEngineCount = 0;
        actualFuelEngineCount = CrudTransaction.updateTransaction(
                new Transaction(-1, 1, 1, new Date(2021, 01, 01)));
        Assert.assertEquals(1, actualFuelEngineCount);
    }

    @Test
    public void shouldUpdateNoTransaction(){
        int actualFuelEngineCount = 0;
        actualFuelEngineCount = CrudTransaction.updateTransaction(
                new Transaction(-2, 1, 1, new Date(2021, 01, 01)));
        Assert.assertEquals(0, actualFuelEngineCount);
    }


    public void shouldDeleteOneTransaction(){
        int actualFuelEngineCount = 0;
        actualFuelEngineCount = CrudTransaction.deleteTransaction(-1);
        Assert.assertEquals(1, actualFuelEngineCount);
    }

    @Test
    public void shouldDeleteNoTransaction(){
        int actualFuelEngineCount = 0;
        actualFuelEngineCount = CrudTransaction.deleteTransaction(-2);
        Assert.assertEquals(0, actualFuelEngineCount);
    }
}
