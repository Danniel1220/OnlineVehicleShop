package com.crystal.ovs.database;

import com.crystal.ovs.dao.User;
import com.crystal.ovs.dao.UserRole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectorTest {
    @Test
    public void should_be_connected_to_the_database() {
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        Assert.assertTrue(DatabaseConnector.isConnectedToDatabase());
    }

    @Test
    public void should_find_properties_file() {
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        Assert.assertTrue(DatabaseConnector.hasFoundFile());
    }

    @Test
    public void should_select_test_user_from_database() throws SQLException {
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        ResultSet databaseResult = databaseConnector.select("select * from onlinevehicleshop.user where id = 1;");

        // If the result set is empty this fails.
        // If it doesn't fail (we found that one test user) it also allows us the fetch that data.
        Assert.assertTrue(databaseResult.next());

        User resultUser = new User(
                databaseResult.getInt("id"),
                databaseResult.getString("name"),
                databaseResult.getString("password"),
                UserRole.valueOf(databaseResult.getString("role"))
        );

        User expectedUser = new User(
                1,
                "John Doe",
                "123456",
                UserRole.CLIENT
        );

        Assert.assertEquals(resultUser, expectedUser);
    }

    @After
    public void destructConnector() {
        DatabaseConnector.destruct();
    }
}
