package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.Image;
import com.crystal.ovs.database.crud.CrudImage;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageController {
    private static boolean isInImageController = true;

    public static void openImageController() throws SQLException, ValidationException {
        while (isInImageController) {
            OutputManager.printFuelEngineControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command) {
                case 1:
                    getAllImages();
                    break;
                case 2:
                    getImagesById();
                    break;
                case 3:
                    createImages();
                    break;
                case 4:
                    updateImages();
                    break;
                case 5:
                    deleteImages();
                    break;
                case 6:
                    isInImageController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllImages() throws SQLException {
        OutputManager.printMessage("All images");

        List<Image> imageList = CrudImage.selectAllImages();
        for(Image image : Objects.requireNonNull(imageList)) {
            OutputManager.printMessage(image.toString());
        }

    }

    private static void getImagesById() throws SQLException {
        OutputManager.printMessage("Choose image's id");
        OutputManager.printLabel("Enter image id");
        Image image = CrudImage.selectImageById(InputManager.readIntegerField());
        if (image != null) {
            OutputManager.printMessage(image.toString());
        } else {
            OutputManager.printMessage(OutputTextType.WARNING, "WARNING: Image not found!");
        }
    }


    private static void deleteImages() {
        OutputManager.printMessage("Insert image's id that you want to delete:");
        CrudImage.deleteImage(InputManager.readIntegerField());
    }

    private static void updateImages() throws SQLException, ValidationException {
        getAllImages();
        OutputManager.printMessage("Choose an  image:");
        OutputManager.printLabel("Enter image's id: ");
        Image image = CrudImage.selectImageById(InputManager.readIntegerField());
        if(image != null) {
            int userOption = 1;
            while(userOption != 0) {
                OutputManager.printMessage("1. Update image url");
                OutputManager.printMessage("2. Update post id");
                OutputManager.printMessage("3. Exit.");
                OutputManager.printMessage("Choose a field to update: ");
                userOption = InputManager.readIntegerField();
                switch(userOption) {
                    case 1: {
                        OutputManager.printMessage("Give  image url ");
                        image.setImageUrl(InputManager.readStringField());
                        break;
                    }
                    case 2: {
                        OutputManager.printMessage("Give  post id: ");
                        image.setPostId(InputManager.readIntegerField());
                        break;
                    }


                    case 3: {
                        break;
                    }
                }
            }
            List<String> validationErrors = validateImage(image);
            if(validateImage(image).size() > 0) {
                try {
                    throw new ValidationException(validationErrors);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            } else {
                CrudImage.updateImage(image);
            }
        } else {
            OutputManager.printMessage(OutputTextType.WARNING,"WARNING: Image not found!");
        }
    }

    private static void createImages() {
        OutputManager.printMessage("Create a new  image:");
        try {
            Image image = readImage();
            if (image != null) {
                CrudImage.insertImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Image readImage() throws ValidationException {
        OutputManager.printMessage("image id: ");
        int imageId = InputManager.readIntegerField();
        OutputManager.printMessage("Image url: ");
        String imageUrl = InputManager.readStringField();
        OutputManager.printMessage("Post id");
        int postId = InputManager.readIntegerField();


        Image image = new Image(1, imageUrl, postId);

        List<String> validationErrors = validateImage(image);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        } else {
            return image;
        }
    }

    public static List<String> validateImage(Image image) {
        List<String> validationErrors = new ArrayList<>();

        if (image.getId() <= 0) {
            validationErrors.add("Id cannot be less than or equal to 0");
        }
        if (image.getImageUrl().isEmpty()) {
            validationErrors.add("Image Url cannot be empty");
        }
        if (image.getPostId() <= 0) {
            validationErrors.add("Id post cannot be less than or equal to 0");
        }



        return validationErrors;
    }
}





