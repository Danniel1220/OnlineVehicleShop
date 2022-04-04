package com.crystal.ovs.appRunner;

import com.crystal.ovs.dao.Post;
import com.crystal.ovs.dao.Transaction;
import com.crystal.ovs.dao.User;
import com.crystal.ovs.database.crud.CrudPost;
import com.crystal.ovs.database.crud.CrudTransaction;
import com.crystal.ovs.inputOutputManager.InputManager;
import com.crystal.ovs.inputOutputManager.OutputManager;
import com.crystal.ovs.inputOutputManager.OutputTextType;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ClientAppRunner {
    private User user;
    public ClientAppRunner(User user) {
        this.user = user;
    }

    /**
     * Client main menu
     */
    public void runClientApp() {
        boolean isInClientController = true;
        while (isInClientController) {
            OutputManager.printClientAppMenu();
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
                    buyCar();
                    break;
                case 4:
                    transactionHistory();
                    break;
                case 5:
                    isInClientController = false;
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

    private void buyCar() {
        Post post = getPostById();
        if(post != null) {
            OutputManager.printMessage(post.toString());
            OutputManager.printMessage("Are you sure you want to buy this car? y | n");
            if(InputManager.readStringField().equals("y")) {
                Transaction transaction = new Transaction(0, post.getId(), this.user.getId(), LocalDate.now());
                CrudTransaction.insertTransaction(transaction);
                OutputManager.printMessage(transaction.toString());
                OutputManager.printMessage("Thank you for the order!");
            }
        }
    }

    /**
     * show entire transaction's history for the current user.
     */
    private void transactionHistory() {
        try {
            List<Transaction> myTransactions = CrudTransaction.selectAllUserTransactions(this.user.getId());
            if(myTransactions.size() == 0) {
                OutputManager.printMessage(OutputTextType.WARNING, "You have no previous transactions!");
            } else {
                OutputManager.printMessage("Your transactions:");
                for(Transaction transaction : myTransactions) {
                    OutputManager.printMessage(transaction.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
