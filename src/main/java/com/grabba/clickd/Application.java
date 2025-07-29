package com.grabba.clickd;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;

public class Application extends javafx.application.Application {
    private MediaPlayer mediaPlayer;



    @Override
    public void start(Stage stage) throws IOException {
        // Load and start looping music
        String musicPath = getClass().getResource("/music/background.mp3").toExternalForm();
        Media media = new Media(musicPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop forever
        mediaPlayer.play();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 570);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Stop music when app closes
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}