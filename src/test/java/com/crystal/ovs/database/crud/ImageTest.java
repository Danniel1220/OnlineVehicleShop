package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImageTest {
    static DatabaseConnector connector = DatabaseConnector.getInstance();;
    final static int EXISTING_ID_1 = -1;
    final static int EXISTING_ID_2 = -2;

    @BeforeClass
    public static void initialize(){
        String electricEngineQuery = String.format(
                "insert into electricengine values(%d, '%s', %d, %d)",
                EXISTING_ID_1, "Type", 100, 100);
        String fuelEngineQuery = String.format(
                "insert into fuelengine values(%d, '%s', %f, %d, %f, '%s', %b, %b, '%s',%f);",
                EXISTING_ID_1, FuelType.GASOLINE, 0f, 0, 0f, EngineLayout.INLINE, false, false, StrokeType.TWO_STROKE, 0f);
        String engineQuery = String.format(
                "insert into engine values(%d, %d, %d, %d, %d)",
                EXISTING_ID_1, EXISTING_ID_1, EXISTING_ID_1, 100, 100);
        String transmissionQuery = String.format(
                "insert into transimission values(%d, '%s', %d)",
                EXISTING_ID_1, "MANUAL", 5);
        String carQuery = String.format(
                "insert into car values(%d, '%s', '%s', '%s', %d, '%s', %d, %d, '%s', %d, '%s')",
                EXISTING_ID_1, "BRAND", "MODEL", "VIN", 2000, "SEDAN", EXISTING_ID_1, EXISTING_ID_1, "AWD", 5, "RED");
        String postQuery = String.format(
                "insert into post values(%d, '%s', '%s', %d, %d, %d)",
                EXISTING_ID_1, "TITLE", "DESCRIPTION", 2000, 10, EXISTING_ID_1);
        String imageQuery = String.format(
                "insert into images values(%d, '%s', %d);",
                EXISTING_ID_1, "LINK", EXISTING_ID_1);
        connector.execute(electricEngineQuery);
        connector.execute(fuelEngineQuery);
        connector.execute(engineQuery);
        connector.execute(transmissionQuery);
        connector.execute(carQuery);
        connector.execute(postQuery);
        connector.execute(imageQuery);
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
        String query = String.format("select * from images where id = %d", EXISTING_ID_1);
        ResultSet resultSet = connector.select(query);

        try {
            Assert.assertTrue(resultSet.next());

            Image expectedImage = CrudImage.getImageFromResultSet(resultSet);
            Image actualImage = CrudImage.selectImageById(EXISTING_ID_1);

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
                    EXISTING_ID_1,
                    "Test image url",
                    EXISTING_ID_1
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
                EXISTING_ID_1,
                "New test image url",
                EXISTING_ID_1
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
        String imageQuery = String.format(
                "insert into images values(%d, '%s', %d);",
                EXISTING_ID_2, "LINK", EXISTING_ID_1);
        connector.execute(imageQuery);
        int actualDeletedRows = CrudImage.deleteImage(EXISTING_ID_2);
        int expectedDeletedRows = 1;

        Assert.assertEquals(expectedDeletedRows, actualDeletedRows);
    }

    @AfterClass
    public static void deleteExisting() {
        String deleteElectricEngine = String.format("delete from electricengine where id=%d", EXISTING_ID_1);
        String deleteFuelEngine = String.format("delete from fuelengine where id=%d", EXISTING_ID_1);
        String deleteEngine = String.format("delete from engine where id=%d", EXISTING_ID_1);
        String deleteTransmission= String.format("delete from transimission where id=%d", EXISTING_ID_1);
        String deleteCar = String.format("delete from car where id=%d", EXISTING_ID_1);
        String deletePost = String.format("delete from post where id=%d", EXISTING_ID_1);
        String deleteImage = String.format("delete from images where postId=%d", EXISTING_ID_1);

        connector.execute(deleteImage);
        connector.execute(deletePost);
        connector.execute(deleteCar);
        connector.execute(deleteTransmission);
        connector.execute(deleteEngine);
        connector.execute(deleteFuelEngine);
        connector.execute(deleteElectricEngine);
    }
}
