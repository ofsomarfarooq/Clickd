package com.grabba.clickd;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartVsCom {
    private MediaPlayer tik = new MediaPlayer(new Media(getClass().getResource("/music/tic.mp3").toString()));
    private MediaPlayer tok = new MediaPlayer(new Media(getClass().getResource("/music/tok.mp3").toString()));


    @FXML
    private Button b00, b01, b02, b10, b11, b12, b20, b21, b22;
    @FXML private Label statusLabel;

    private char[][] board = new char[3][3];
    private boolean playerTurn = true;
    private boolean gameOver = false;

    @FXML
    public void initialize() {
        for (Button btn : getAllButtons()) {
            btn.setOnAction(e -> handlePlayerMove(btn));
        }
        resetBoard();
    }

    private void handlePlayerMove(Button btn) {
        if (!playerTurn || gameOver || !btn.getText().isEmpty()) return;

        tok.stop();
        tik.play();
        btn.setText("X");
        updateBoard();
        if (checkWin('X')) {
            statusLabel.setText("You win!");
            gameOver = true;
            return;
        }
        if (isDraw()) {
            statusLabel.setText("Draw!");
            gameOver = true;
            return;
        }
        playerTurn = false;

        computerMove();
    }

    private void computerMove() {






        List<Button> empty = new ArrayList<>();
        for (Button btn : getAllButtons()) {
            if (btn.getText().isEmpty()) empty.add(btn);
        }
        if (empty.isEmpty()) return;
        Button move = empty.get(new Random().nextInt(empty.size()));
        tik.stop();
        tok.play();

        move.setText("O");
        updateBoard();
        if (checkWin('O')) {
            statusLabel.setText("Computer wins!");
            gameOver = true;
            return;
        }
        if (isDraw()) {
            statusLabel.setText("Draw!");
            gameOver = true;
            return;
        }
        playerTurn = true;
    }

    private void updateBoard() {
        board[0][0] = b00.getText().isEmpty() ? ' ' : b00.getText().charAt(0);
        board[0][1] = b01.getText().isEmpty() ? ' ' : b01.getText().charAt(0);
        board[0][2] = b02.getText().isEmpty() ? ' ' : b02.getText().charAt(0);
        board[1][0] = b10.getText().isEmpty() ? ' ' : b10.getText().charAt(0);
        board[1][1] = b11.getText().isEmpty() ? ' ' : b11.getText().charAt(0);
        board[1][2] = b12.getText().isEmpty() ? ' ' : b12.getText().charAt(0);
        board[2][0] = b20.getText().isEmpty() ? ' ' : b20.getText().charAt(0);
        board[2][1] = b21.getText().isEmpty() ? ' ' : b21.getText().charAt(0);
        board[2][2] = b22.getText().isEmpty() ? ' ' : b22.getText().charAt(0);
    }

    private boolean checkWin(char c) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == c && board[i][1] == c && board[i][2] == c) return true;
            if (board[0][i] == c && board[1][i] == c && board[2][i] == c) return true;
        }
        if (board[0][0] == c && board[1][1] == c && board[2][2] == c) return true;
        if (board[0][2] == c && board[1][1] == c && board[2][0] == c) return true;
        return false;
    }

    private boolean isDraw() {
        for (Button btn : getAllButtons()) {
            if (btn.getText().isEmpty()) return false;
        }
        return true;
    }

    private List<Button> getAllButtons() {
        return List.of(b00, b01, b02, b10, b11, b12, b20, b21, b22);
    }

    private void resetBoard() {
        for (Button btn : getAllButtons()) btn.setText("");
        statusLabel.setText("Your turn!");
        playerTurn = true;
        gameOver = false;
    }

    @FXML
    private void onReset() {
        resetBoard();
    }

    @FXML
    public void backToMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grabba/clickd/index.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 700, 570);
            scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
