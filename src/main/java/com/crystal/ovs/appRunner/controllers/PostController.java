package com.crystal.ovs.appRunner.controllers;

import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;

public class PostController {
    private static boolean isInPostController = true;

    public static void openPostController(){
        while(isInPostController){
            OutputManager.printPostControllerMenu();
            int command = InputManager.readIntegerField();

            switch (command){
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
    }

    private static void getPostById() {
    }

    private static void createPost() {
    }

    private static void updatePost() {
    }

    private static void deletePost() {
    }
}
