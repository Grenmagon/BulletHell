package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinalWindow extends Window {

    private Button gameOver;
    private Button newHighScore;
    private Label playerName;
    private Label points;

    public FinalWindow(Stage stage, BaseWindow base, String name) {
        super(stage, base, name);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);// Выравниваем содержимое по центру
        layout.setPrefSize(GameInformations.WINDOWSIZEX, GameInformations.WINDOWSIZEY);


        gameOver = new Button("Game Over");
        gameOver.setOnMouseClicked(mouseEvent -> backToStart());
        gameOver.setMinWidth(210);
        gameOver.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );

        System.out.println("Current player name : " + GameInformations.playername);
        playerName = new Label(GameInformations.playername);// Добавляем имя
        playerName.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 20px;");


        points = new Label(Integer.toString(GameInformations.highscore));// Добавляем заголовок
        points.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 20px;");


        newHighScore = new Button("New High Score");
        newHighScore.setOnMouseClicked(mouseEvent -> highScoreWindow());
        newHighScore.setMinWidth(210);
        newHighScore.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;");        // Толщина обводки

        layout.getChildren().addAll(gameOver, playerName, points,newHighScore);
        this.getChildren().add(layout);
    }

    @Override
    protected void doActive() {
        playerName.setText(GameInformations.playername);
        points.setText(GameInformations.highscore + "");
    }

    @Override
    public void checkActive() {

    }

    @Override
    protected void doNotActive() {

    }

    private void backToStart() {
        Window.setActive("StartWindow");

    }

    private void highScoreWindow() {
        Window.setActive("HighScoreWindow");

    }
}
