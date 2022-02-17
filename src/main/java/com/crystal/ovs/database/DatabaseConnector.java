package com.crystal.ovs.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {
    private static DatabaseConnector instance = null;

    private static Statement databaseStatement;

    private static boolean hasFoundFile;
    private static boolean isConnectedToDatabase;

    private DatabaseConnector()
    {
        try (FileReader propertiesReader = new FileReader("databaseCredentials.properties")) {
            hasFoundFile = true;

            Connection databaseConnection;

            Properties properties = new Properties();
            properties.load(propertiesReader);

            System.out.println("Attempting connection to database...");
            databaseConnection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
            System.out.println("Connection established!");

            databaseStatement = databaseConnection.createStatement();

            isConnectedToDatabase = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            hasFoundFile = false;
            System.out.println("Couldn't find the properties file...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't read the properties file...");
        } catch (SQLException e) {
            isConnectedToDatabase = false;
            e.printStackTrace();
            System.out.println("Couldn't connect to the database...");
        }
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }

        return instance;
    }

    public ResultSet select(String query) {
        try {
            return databaseStatement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception occurred while performing a select on the database...");
        }
        return null;
    }

    public void execute(String query)
    {
        try
        {
            databaseStatement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Exception occurred while executing a query on the database...");
        }
    }

    public static void destruct() {
        instance = null;
    }

    public static boolean hasFoundFile() {
        return hasFoundFile;
    }

    public static boolean isConnectedToDatabase() {
        return isConnectedToDatabase;
    }
}
