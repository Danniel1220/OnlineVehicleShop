package com.crystal.ovs.database.crud;

import com.crystal.ovs.controllers.FuelEngineController;
import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CrudPostTest {
    static DatabaseConnector connector = DatabaseConnector.getInstance();

    final int EXISTING_ID = 20;

    @Test
    public void shouldSelectAllPosts(){
        try {
            List<Post> postList = CrudPost.selectAllPosts();
            ResultSet resultSet = connector.select("select count(id) as count from post;");
            resultSet.next();
            int count = resultSet.getInt("count");
            Assert.assertEquals(count, postList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSelectPostWithSpecifiedId(){
        ResultSet resultSet = connector.select(String.format("select * from post where id = %d", EXISTING_ID));

        try {
            Assert.assertTrue(resultSet.next());

            Post expectedPost = CrudPost.getPostFromResultSet(resultSet);
            Post actualPost = CrudPost.selectPostById(EXISTING_ID);

            Assert.assertEquals(expectedPost, actualPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldInsertPost(){
        int actualPostCount = 0;
        Car car = Car.builder().id(120).build();
        try {
            actualPostCount = CrudPost.insertPost(new Post(
                    1,
                    "Post Title",
                    "Post Description",
                    120d,
                    12,
                    car
            ));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, actualPostCount);
    }

    @Test
    public void shouldUpdatePost() throws ValidationException {
        int actualPostCount;
        Car car = Car.builder().id(120).build();
        Post post = new Post(
                EXISTING_ID,
                "Updated Post Title",
                "Updated Post Desc",
                120d,
                12,
                car
        );

        actualPostCount = CrudPost.updatePost(post);
        Assert.assertEquals(1, actualPostCount);
    }

    @Test
    public void shouldDeletePost(){
        int actualDeletedRows = CrudPost.deletePost(EXISTING_ID);
        int expectedDeletedRows = 1;

        Assert.assertEquals(expectedDeletedRows, actualDeletedRows);

        try {
            Post actualPost = CrudPost.selectPostById(EXISTING_ID);
            Post expectedPost = null;

            Assert.assertEquals(actualPost, expectedPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
