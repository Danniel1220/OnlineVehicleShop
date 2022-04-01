package com.crystal.ovs.database.crud;

import com.crystal.ovs.dao.Image;
import com.crystal.ovs.database.DatabaseConnector;
import com.crystal.ovs.exceptions.ValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudImage {
    private static DatabaseConnector connector;

    static {
        connector = DatabaseConnector.getInstance();
    }
    private static final String IMAGE_TABLE_NAME = "image";

    private static final String IMAGE_ID_COLUMN = "id";
    private static final String IMAGE_IMAGE_URL_COLUMN = "imageUrl";
    private static final String IMAGE_POST_ID_COLUMN = "postId";

    public static List<Image> selectAllImages() throws SQLException{
        List<Image> fuelEngineList = new ArrayList<>();

        String query = String.format("SELECT * FROM %s;",IMAGE_TABLE_NAME);
        ResultSet resultSet = connector.select(query);
        while(resultSet.next()){
            fuelEngineList.add(getImageFromResultSet(resultSet));
        }

        return fuelEngineList;
    }

    public static Image getImageById(int id) throws SQLException {
        Image image = null;
        String query = String.format("SELECT * FROM %s WHERE %s=%d",
                IMAGE_TABLE_NAME, IMAGE_ID_COLUMN, id);
        ResultSet resultSet = connector.select(query);
        while( resultSet.next()) {
            image = getImageFromResultSet(resultSet);
        }
        return image;
    }

    public static int insertImage(Image image) throws ValidationException, SQLException {
        List<String> validationErrors = validateImage(image);
        if(validationErrors.size() > 0){
            throw new ValidationException(validationErrors);
        }
        String query = String.format(
                "INSERT INTO `%s` (%s, %s) " +
                        "values('%s',%d);",
                IMAGE_TABLE_NAME, IMAGE_IMAGE_URL_COLUMN, IMAGE_POST_ID_COLUMN,
                image.getImageUrl(), image.getPostId());

        return connector.execute(query);
    }

    public static int updateImage(Image newImage) throws ValidationException, SQLException {
        List<String> validationErrors = validateImage(newImage);
        if(validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        String query = String.format("UPDATE `%s` SET " +
                        "%s = '%s', %s = %d" +
                        "WHERE %s = %d;",
                IMAGE_TABLE_NAME, IMAGE_IMAGE_URL_COLUMN, newImage.getImageUrl(),
                IMAGE_POST_ID_COLUMN, newImage.getPostId(), IMAGE_ID_COLUMN ,newImage.getPostId());

        return connector.execute(query);
    }

    public static int deleteImage(int id){
        String query = String.format("DELETE FROM %s WHERE %s=%d",
                IMAGE_TABLE_NAME, IMAGE_ID_COLUMN, id);
        return connector.execute(query);
    }

    private static List<String> validateImage(Image image) throws SQLException {
        List<String> validationErrors = new ArrayList<>();

        if(image.getImageUrl().isBlank()){
            validationErrors.add("Missing image URL");
        }
        if(CrudPost.selectPostById(image.getId()) == null){
            validationErrors.add("Thee is no post with this id");
        }

        return validationErrors;

    }

    private static Image getImageFromResultSet(ResultSet resultSet) throws SQLException {
        return new Image(resultSet.getInt(IMAGE_ID_COLUMN),
                resultSet.getString(IMAGE_IMAGE_URL_COLUMN),
                resultSet.getInt(IMAGE_POST_ID_COLUMN));
    }
}
