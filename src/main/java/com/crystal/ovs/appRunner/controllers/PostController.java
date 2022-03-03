package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.dao.*;
import com.crystal.ovs.database.crud.CrudCar;
import com.crystal.ovs.database.crud.CrudFuelEngine;
import com.crystal.ovs.database.crud.CrudPost;
import com.crystal.ovs.exceptions.ValidationException;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

import java.sql.SQLException;
import java.util.List;

public class PostController {
    private static boolean isInPostController = true;

    public static void openPostController() {
        while(isInPostController) {
            OutputManager.printPostControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command) {
                case 1:
                    getAllPosts();
                    break;
                case 2:
                    getPostById();
                    break;
                case 3:
                    createPost();
                    break;
                case 4:
                    updatePost();
                    break;
                case 5:
                    deletePost();
                    break;
                case 6:
                    isInPostController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private static void getAllPosts() {
        OutputManager.printMessage("Posts");
        try {
            List<Post> postList = CrudPost.selectAllPosts();
            OutputManager.printPostTableHead();
            for (Post post : postList) {
                OutputManager.printPost(post);
            }
        } catch (SQLException e) {
            OutputManager.printMessage("There was a problem connecting to the database");
        }
    }

    private static void getPostById() {
        OutputManager.printMessage("Chose a post");
        OutputManager.printLabel("Enter post id");
        int id = InputManager.readIntegerField();

        try {
            Post post = CrudPost.selectPostById(id);
            if (post != null) {
                OutputManager.printPost(post);
            } else {
                OutputManager.printMessage("There is no post with that Id");
            }
        } catch (SQLException e) {
            OutputManager.printMessage("There was a problem connecting to the database");
        }
    }

    private static void createPost() {
        try {
            Post post = readPost();
            CrudPost.insertPost(post);
        } catch (ValidationException e) {
            OutputManager.printValidationErrors(e.getValidationErrors());
        }
    }

    private static Post readPost() throws ValidationException {
        OutputManager.printLabel("Title");
        String postTitle = InputManager.readStringField();
        OutputManager.printLabel("Description");
        String postDescription = InputManager.readStringField();
        OutputManager.printLabel("Price");
        float postPrice = InputManager.readFloatField();
        OutputManager.printLabel("Available");
        int postAvailable = InputManager.readIntegerField();
        OutputManager.printLabel("Car Id");
        int postCarId = InputManager.readIntegerField();


        List<String> validationErrors = validatePost(postTitle, postDescription, postPrice, postAvailable, postCarId);
        if (validationErrors.size() > 0) {
            throw new ValidationException(validationErrors);
        }
        else {
            return new Post(-1, postTitle, postDescription, postPrice,
                    postAvailable, CrudCar.getCarById(postCarId));
        }
    }



    private static void updatePost() {
        OutputManager.printMessage("Chose a post");
        OutputManager.printLabel("Enter post id");
        int id = InputManager.readIntegerField();

        try {
            Post post = CrudPost.selectPostById(id);
            if (post != null) {
                OutputManager.printPost(post);
                Post newPost = readPost();
                newPost.setId((post.getId()));
                CrudPost.updatePost(newPost);
            } else {
                OutputManager.printMessage("There is no post engine with that Id");
            }
        } catch (SQLException e) {
            OutputManager.printMessage("There was a problem connecting to the database");
        } catch (ValidationException e) {
            OutputManager.printValidationErrors(e.getValidationErrors());
        }
    }

    private static void deletePost() {
        OutputManager.printMessage("Chose a post");
        OutputManager.printLabel("Enter post id");
        int id = InputManager.readIntegerField();

        try {
            Post post = CrudPost.selectPostById(id);
            if (post != null) {
                OutputManager.printPost(post);
                OutputManager.printLabel("Are you sure you want to delete this post? (y/n)");
                String response = InputManager.readStringField();

                if(response.equalsIgnoreCase("y")){
                    CrudPost.deletePost(post.getId());
                }
            } else {
                OutputManager.printMessage("There is no post with that Id");
            }
        } catch (SQLException e) {
            OutputManager.printMessage("There was a problem connecting to the database");
        }
    }

    private static List<String> validatePost(String postTitle, String postDescription, float postPrice, int postAvailable, int postCarId) {
        return null;
    }
}
