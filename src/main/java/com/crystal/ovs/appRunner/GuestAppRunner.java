package com.crystal.ovs.appRunner;

import com.crystal.ovs.dao.Post;
import com.crystal.ovs.database.crud.CrudPost;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class GuestAppRunner {
    /**
     * Guest main menu
     */
    public void runGuestApp() {
        boolean isInGuestController = true;
        while (isInGuestController) {
            OutputManager.printGuestAppMenu();
            int command = InputManager.readIntegerField();

            switch (command) {
                case 1:
                    getPosts();
                    break;
                case 2:
                    Post searchedPost = getPostById();
                    if(searchedPost != null) {
                        OutputManager.printMessage(searchedPost.toString());
                    } else {
                        OutputManager.printMessage(OutputTextType.WARNING, "Post not found!");
                    }
                    break;
                case 3:
                    isInGuestController = false;
                    break;
                default:
                    OutputManager.invalidCommandMessage();
                    break;
            }
        }
    }

    private void getPosts() {
        try {
            List<Post> posts = CrudPost.selectAllPosts();
            if(posts.size() > 0) {
                for(Post post : Objects.requireNonNull(posts)) {
                    OutputManager.printMessage(post.toString());
                }
            } else {
                OutputManager.printMessage(OutputTextType.WARNING, "There are no posts to show!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Post getPostById() {
        OutputManager.printMessage("What is the id of the post you are looking for?");
        try {
            return CrudPost.selectPostById(InputManager.readIntegerField());
        } catch (Exception ignored) {}
        return null;
    }
}
