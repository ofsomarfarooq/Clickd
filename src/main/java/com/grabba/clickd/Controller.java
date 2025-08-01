package com.grabba.clickd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


import java.io.IOException;

public class Controller {

    @FXML
    private Label welcomeText;
    private AnchorPane rootPane;
    @FXML
    void exitbutton(ActionEvent event) {

    }

    @FXML
    void optionbutton(ActionEvent event) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/music/begin.mp3").toString()));
            mediaPlayer.play();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startvscom.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene gameScene = new Scene(root);
            stage.setScene(gameScene);
            stage.setTitle("Options");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading GameScene.fxml: " + e.getMessage());
        }




    }
    @FXML
    void startbutton(ActionEvent event) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/music/begin.mp3").toString()));
            mediaPlayer.play();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene gameScene = new Scene(root);
            stage.setScene(gameScene);
            stage.setTitle("ClickD Game");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading GameScene.fxml: " + e.getMessage());
        }
    }


    @FXML
    private Slider volumeSlider;

    private MediaPlayer mediaPlayer;

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        if (volumeSlider != null) {
            volumeSlider.valueProperty().bindBidirectional(mediaPlayer.volumeProperty());
        }
    }



    @FXML
    public void initialize() {
        if (mediaPlayer == null) {
            String musicPath = getClass().getResource("/music/background.mp3").toExternalForm();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
        if (volumeSlider != null) {
            volumeSlider.setValue(0.5);
            volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(newVal.doubleValue());
                }
            });
        }
    }






}
