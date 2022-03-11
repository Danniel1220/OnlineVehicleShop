package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrudTransaction {
    private static final String TRANSACTION_TABLE_NAME = "transaction";
    private static final String TRANSACTION_ID_COLUMN = "id";
    private static final String TRANSACTION_ID_USER_COLUMN = "idUser";
    private static final String TRANSACTION_ID_POST_COLUMN = "idPost";
    private static final String TRANSACTION_DATE_COLUMN = "date";

    private static DatabaseConnector connector;
    static {
        connector = DatabaseConnector.getInstance();
    }

    public static List<Transaction> selectAllUserTransactions(int userId) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        String query = "SELECT * FROM " + TRANSACTION_TABLE_NAME +
                " WHERE " + TRANSACTION_ID_USER_COLUMN + " = " + userId + ";";

        ResultSet resultSet = connector.select(query);
        while(resultSet.next()){
            transactionList.add(getTransactionFromResultSet(resultSet));
        }
        return transactionList;
    }

    public static int insertTransaction(Transaction transaction){
        String query = "INSERT INTO `" + TRANSACTION_TABLE_NAME +
                "` (" + TRANSACTION_ID_USER_COLUMN + ", " +
                TRANSACTION_ID_POST_COLUMN + ", " +
                TRANSACTION_DATE_COLUMN + ") VALUES (" +
                transaction.getUserId() + ", " +
                transaction.getPostId() + ", " +
                "date('" + extractDate(transaction.getDate()) + "'));";

        return connector.execute(query);
    }

    public static int updateTransaction(Transaction newTransaction){

        String query = "UPDATE `" + TRANSACTION_TABLE_NAME + "` SET " +
                TRANSACTION_ID_USER_COLUMN + " = " + newTransaction.getUserId() + ", " +
                TRANSACTION_ID_POST_COLUMN + " = " + newTransaction.getPostId() + ", " +
                TRANSACTION_DATE_COLUMN + " = date('" + extractDate(newTransaction.getDate()) +
                "') WHERE " + TRANSACTION_ID_COLUMN + " = " + newTransaction.getId() + ";";

        return connector.execute(query);
    }

    public static int deleteTransaction(int id){
        String query = "DELETE FROM " + TRANSACTION_TABLE_NAME +
                " WHERE " + TRANSACTION_ID_COLUMN + " = " + id + ";";
        return connector.execute(query);
    }

    private static Transaction getTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        return new Transaction(
                resultSet.getInt(TRANSACTION_ID_COLUMN),
                resultSet.getInt(TRANSACTION_ID_POST_COLUMN),
                resultSet.getInt(TRANSACTION_ID_USER_COLUMN),
                resultSet.getDate(TRANSACTION_DATE_COLUMN));
    }

    private static String extractDate(Date date){
        return date.getYear() + "-" +
                date.getMonth() + 1 + "-" +
                date.getDate();
    }
}
