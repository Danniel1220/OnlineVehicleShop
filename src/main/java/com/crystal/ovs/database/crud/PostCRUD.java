package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostCRUD {
    private final static DatabaseConnector connector;

    static {
        connector = DatabaseConnector.getInstance();
    }

    public static List<Post> selectAllPosts() throws SQLException {
        List<Post> postList = new ArrayList<>();

        String query = "select * from post;";
        ResultSet resultSet = connector.select(query);
        while(resultSet.next()){
            postList.add(getPostFromResultSet(resultSet));
        }

        return postList;
    }

    public static Post selectPostById(int id) throws SQLException {
        String query = String.format("select * from post where id=%d", id);
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
                "insert into `post` (id, title, description, price, available, carId) values(%d, %s, %s, %f, %d, %b);",
                post.getId(), post.getTitle(), post.getDescription(), post.getPrice(), post.getAvailable(), post.getCar());

        return connector.execute(query);
    }

    public static int updatePost(Post post) throws ValidationException {
        List<String> validationErrors = validatePost(post);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }

        String query = String.format("update `post` set " +
                        "title = %s, " +
                        "description = %s, " +
                        "price = %f, " +
                        "available = %d, " +
                        "carId = '%b', " +
                        "where id = %d;",
                post.getTitle(), post.getDescription(),
                post.getPrice(), post.getAvailable(),
                post.getCar().getId(), post.getId());

        return connector.execute(query);
    }

    public static int deletePost(int id){
        String query = String.format("delete from post where id = %d;", id);
        return connector.execute(query);
    }

    public static Post getPostFromResultSet(ResultSet resultSet) throws SQLException {
        return new Post(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getInt("available"),
                resultSet.getObject("carId", Car.class)
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
