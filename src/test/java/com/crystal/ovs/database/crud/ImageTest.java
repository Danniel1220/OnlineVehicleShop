package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImageTest {
    static DatabaseConnector connector = DatabaseConnector.getInstance();;

    final static int EXISTING_ID = -1;

    @BeforeClass
    public static void initialize(){
        connector = DatabaseConnector.getInstance();
        String query = String.format(
                "insert into `images` values(%d, '%s', %d);",
                EXISTING_ID, "Whatever", 1);

        connector.execute(query);
    }

    @Test
    public void shouldReturnAllImages(){
        try{
            List<Image> postList = CrudImage.selectAllImages();
            ResultSet resultSet = connector.select("select count(id) as count from images;");
            resultSet.next();
            int count = resultSet.getInt("count");
            Assert.assertEquals(count, postList.size());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSelectImageWithSpecifiedId(){
        String query = String.format("select * from images where id = %d", 17);
        ResultSet resultSet = connector.select(query);

        try {
            Assert.assertTrue(resultSet.next());

            Image expectedImage = CrudImage.getImageFromResultSet(resultSet);
            Image actualImage = CrudImage.selectImageById(17);

            Assert.assertEquals(expectedImage, actualImage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldInsertImage(){
        int actualImageCount = 0;
        try {
            actualImageCount = CrudImage.insertImage(new Image(
                    EXISTING_ID,
                    "Post Title",
                    1
            ));
        } catch (ValidationException | SQLException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1, actualImageCount);
    }

    @Test
    public void shouldUpdateImage()  {
        int actualImageCount;
        Image image = new Image(
                17,
                "Post Title",
                1
        );

        try {
            actualImageCount = CrudImage.updateImage(image);
            Assert.assertEquals(1, actualImageCount);
        } catch (ValidationException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldDeleteImage(){
        int actualDeletedRows = CrudImage.deleteImage(EXISTING_ID);
        int expectedDeletedRows = 1;

        Assert.assertEquals(expectedDeletedRows, actualDeletedRows);

        try {
            Image actualImage = CrudImage.selectImageById(EXISTING_ID);
            Image expectedImage = null;

            Assert.assertEquals(actualImage, expectedImage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
