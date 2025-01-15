package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.Objects;

public class StartWindow extends Window {

    private Button start;
    private Button playerName;
    private Label bulletHell;


    public StartWindow(Stage stage, BaseWindow base, String name) {
        super(stage, base, name);

        VBox layout = new VBox(20);  // Создаем VBox для организации кнопок
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setFillWidth(true);


        layout.setPrefWidth(GameInformations.WINDOWSIZEX);
        layout.setPrefHeight(GameInformations.WINDOWSIZEY);

        bulletHell = new Label("Bullet Hell");
        bulletHell.setMinWidth(210);
        bulletHell.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 50px; "
        );
        layout.getChildren().add(bulletHell);

        start = new Button("Play Game");
        start.setOnMouseClicked(mouseEvent -> startGame());
        start.setMinWidth(210);
        start.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );

        playerName = new Button("Enter Player Name");
        playerName.setOnMouseClicked(mouseEvent -> enterPlayerName());
        playerName.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );
        layout.getChildren().add(playerName);
        layout.getChildren().add(start);




        this.getChildren().add(layout);


    }


    @Override
    protected void doActive() {

    }

    @Override
    public void checkActive() {

    }

    @Override
    protected void doNotActive() {

    }

    private void startGame() {
        Window.setActive("DifficultySelectionWindow");

    }

    private void enterPlayerName() {
        Window.setActive("EnterPlayerNameWindow");

    }
}
