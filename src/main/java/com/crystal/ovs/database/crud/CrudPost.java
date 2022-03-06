package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CrudPost {
    private static final String POST_TABLE_NAME = "post";

    private static final String POST_ID_COLUMN = "id";
    private static final String POST_TITLE_COLUMN = "title";
    private static final String POST_DESCRIPTION_COLUMN = "description";
    private static final String POST_PRICE_COLUMN = "price";
    private static final String POST_AVAILABLE_COLUMN = "available";
    private static final String POST_CAR_ID_COLUMN = "carId";


    private final static DatabaseConnector connector;

    static {
        connector = DatabaseConnector.getInstance();
    }

    public static List<Post> selectAllPosts() throws SQLException {
        List<Post> postList = new ArrayList<>();

        String query = "select * from " + POST_TABLE_NAME+ ";";
        ResultSet resultSet = connector.select(query);
        while(resultSet.next()){
            postList.add(getPostFromResultSet(resultSet));
        }

        return postList;
    }

    public static Post selectPostById(int id) throws SQLException {
        String query = String.format("select * from " + POST_TABLE_NAME + " where " + POST_ID_COLUMN + "=%d", id);
        ResultSet resultSet = connector.select(query);
        resultSet.next();
        return getPostFromResultSet(resultSet);
    }

    public static int insertPost(Post post) throws ValidationException {
        List<String> validationErrors = validatePost(post);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }

        String query = String.format(
                "insert into post (" + POST_TITLE_COLUMN + ", " + POST_DESCRIPTION_COLUMN + ", " +
                        POST_PRICE_COLUMN + ", " + POST_AVAILABLE_COLUMN + ", " + POST_CAR_ID_COLUMN + ") values('%s', '%s', %f, %d, %d);",
                post.getTitle(), post.getDescription(), post.getPrice(), post.getAvailable(), post.getCar().getId());

        connector.execute("SET FOREIGN_KEY_CHECKS = 0;");

        return connector.execute(query);
    }

    public static int updatePost(Post post) throws ValidationException {
        List<String> validationErrors = validatePost(post);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }

        String query = String.format("update `" + POST_TABLE_NAME + "` set " +
                        POST_TITLE_COLUMN + " = '%s', " +
                        POST_DESCRIPTION_COLUMN + " = '%s', " +
                        POST_PRICE_COLUMN + " = %f, " +
                        POST_AVAILABLE_COLUMN + " = %d, " +
                        POST_CAR_ID_COLUMN + " = '%d' " +
                        "where " + POST_ID_COLUMN +" = %d;",
                post.getTitle(), post.getDescription(),
                post.getPrice(), post.getAvailable(),
                post.getCar().getId(), post.getId());

        System.out.println(query);

        return connector.execute(query);
    }

    public static int deletePost(int id){
        String query = String.format("delete from " + POST_TABLE_NAME +" where " + POST_ID_COLUMN + " = %d;", id);
        return connector.execute(query);
    }

    public static Post getPostFromResultSet(ResultSet resultSet) throws SQLException {
        return new Post(
                resultSet.getInt(POST_ID_COLUMN),
                resultSet.getString(POST_TITLE_COLUMN),
                resultSet.getString(POST_DESCRIPTION_COLUMN),
                resultSet.getDouble(POST_PRICE_COLUMN),
                resultSet.getInt(POST_AVAILABLE_COLUMN),
                resultSet.getObject(POST_AVAILABLE_COLUMN, Car.class)
        );
    }

    public static List<String> validatePost(Post post){
        List<String> validationErrors = new ArrayList<>();

        if(post.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if(post.getTitle().isEmpty()) {
            validationErrors.add("Title cannot be empty");
        }
        if(post.getDescription().isEmpty()) {
            validationErrors.add("Description cannot be empty");
        }
        if(post.getPrice() <= 0) {
            validationErrors.add("Price cannot be less than or equal to 0");
        }
        if(post.getAvailable() <= 0) {
            validationErrors.add("Available amount cannot be less than or equal to 0");
        }
        if(Objects.isNull(post.getCar())) {
            validationErrors.add("Car object cannot be null");
        }

        return validationErrors;
    }
}
