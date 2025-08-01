package com.grabba.clickd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartController{
    private MediaPlayer tik = new MediaPlayer(new Media(getClass().getResource("/music/tic.mp3").toString()));
    private MediaPlayer tok = new MediaPlayer(new Media(getClass().getResource("/music/tok.mp3").toString()));





    @FXML
    private Label statusLabel;
    @FXML
    private Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22;

    private char[][] board = new char[3][3];
    private boolean xTurn = true;
    private boolean gameOver = false;

    @FXML
    public void initialize() {
        resetBoard();
    }

    @FXML
    public void handleMove(ActionEvent event) {
        if (gameOver) return;

        Button btn = (Button) event.getSource();
        int row = -1, col = -1;
        if (btn == btn00) {
            row = 0;
            col = 0;
        } else if (btn == btn01) {
            row = 0;
            col = 1;
        } else if (btn == btn02) {
            row = 0;
            col = 2;
        } else if (btn == btn10) {
            row = 1;
            col = 0;
        } else if (btn == btn11) {
            row = 1;
            col = 1;
        } else if (btn == btn12) {
            row = 1;
            col = 2;
        } else if (btn == btn20) {
            row = 2;
            col = 0;
        } else if (btn == btn21) {
            row = 2;
            col = 1;
        } else if (btn == btn22) {
            row = 2;
            col = 2;
        }

        if (board[row][col] == '\0') {

            if (xTurn){
                board[row][col] = 'X';
                btn.getStyleClass().remove("no-bg-btn");
                btn.getStyleClass().add("sherif-btn");
                tok.stop();
                tik.play();

            } else {
                board[row][col] = 'O';
                btn.getStyleClass().remove("no-bg-btn");
                btn.getStyleClass().add("outlaw-btn");
                tik.stop();
                tok.play();

            }
            btn.setText(String.valueOf(board[row][col]));
            if (checkWin()) {
                statusLabel.setText("Player " + (xTurn ? "X" : "O") + " wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                statusLabel.setText("It's a draw!");
                gameOver = true;
            } else {
                xTurn = !xTurn;
                statusLabel.setText("Tic-Tac-Toe: Player " + (xTurn ? "X" : "O") + "'s turn");
            }
        }
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

    private void resetBoard() {
        board = new char[3][3];

        btn00.getStyleClass().remove("sherif-btn");
        btn00.getStyleClass().remove("outlaw-btn");
        btn00.getStyleClass().add("no-bg-btn");

        btn01.getStyleClass().remove("sherif-btn");
        btn01.getStyleClass().remove("outlaw-btn");
        btn01.getStyleClass().add("no-bg-btn");

        btn02.getStyleClass().remove("sherif-btn");
        btn02.getStyleClass().remove("outlaw-btn");
        btn02.getStyleClass().add("no-bg-btn");

        btn10.getStyleClass().remove("sherif-btn");
        btn10.getStyleClass().remove("outlaw-btn");
        btn10.getStyleClass().add("no-bg-btn");

        btn11.getStyleClass().remove("sherif-btn");
        btn11.getStyleClass().remove("outlaw-btn");
        btn11.getStyleClass().add("no-bg-btn");

        btn12.getStyleClass().remove("sherif-btn");
        btn12.getStyleClass().remove("outlaw-btn");
        btn12.getStyleClass().add("no-bg-btn");

        btn20.getStyleClass().remove("sherif-btn");
        btn20.getStyleClass().remove("outlaw-btn");
        btn20.getStyleClass().add("no-bg-btn");

        btn21.getStyleClass().remove("sherif-btn");
        btn21.getStyleClass().remove("outlaw-btn");
        btn21.getStyleClass().add("no-bg-btn");

        btn22.getStyleClass().remove("sherif-btn");
        btn22.getStyleClass().remove("outlaw-btn");
        btn22.getStyleClass().add("no-bg-btn");

        btn00.setText("");
        btn01.setText("");
        btn02.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        btn20.setText("");
        btn21.setText("");
        btn22.setText("");
        xTurn = true;
        gameOver = false;
        statusLabel.setText("Tic-Tac-Toe: Player X's turn");
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // rows or columns
            if (board[i][0] != '\0' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
            if (board[0][i] != '\0' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        // diagonals
        if (board[0][0] != '\0' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if (board[0][2] != '\0' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
        return false;
    }

    private boolean isBoardFull() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == '\0') return false;
        return true;
    }

    public void resetGame(ActionEvent actionEvent) {
        resetBoard();
    }
}


