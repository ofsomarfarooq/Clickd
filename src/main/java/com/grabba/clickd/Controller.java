package com.grabba.clickd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {



    @FXML
    private Label welcomeText;
    private AnchorPane rootPane;

    @FXML
    void tictactoebutton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tic_tac_toe.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene gameScene = new Scene(root);
            stage.setScene(gameScene);
            stage.setTitle("Tic-Tac-Toe");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void exitbutton(ActionEvent event) {

    }

    @FXML
    void optionbutton(ActionEvent event) {
        try {
            // Load the FXML file for the game scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startvscom.fxml"));
            Parent root = loader.load();

            // Get the current stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            Scene gameScene = new Scene(root);
            stage.setScene(gameScene);
            stage.setTitle("Options"); // Set a new title for the game window
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // In a real application, you might show an error dialog to the user
            System.err.println("Error loading GameScene.fxml: " + e.getMessage());
        }




    }
    @FXML
    void startbutton(ActionEvent event) {
        try {
            // Load the FXML file for the game scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
            Parent root = loader.load();

            // Get the current stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            Scene gameScene = new Scene(root);
            stage.setScene(gameScene);
            stage.setTitle("ClickD Game"); // Set a new title for the game window
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // In a real application, you might show an error dialog to the user
            System.err.println("Error loading GameScene.fxml: " + e.getMessage());
        }
    }


}
