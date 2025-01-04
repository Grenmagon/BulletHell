package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.Objects;

public class StartWindow extends Window {

    private Button start;
    private Button playerName;
    private Button mute;
    private Button highScore;


    public StartWindow(Stage stage, BaseWindow base, String name) {
        super(stage, base, name);

        VBox layout = new VBox(20);  // Создаем VBox для организации кнопок
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setFillWidth(true);


        layout.setPrefWidth(GameInformations.WINDOWSIZEX);
        layout.setPrefHeight(GameInformations.WINDOWSIZEY);

        start = new Button("Play Game");
        start.setOnMouseClicked(mouseEvent -> startGame());
        start.setMinWidth(210);
        start.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );
        layout.getChildren().add(start);

        //start.setTranslateX(GameInformations.WINDOWSIZEX / 2-start.getWidth()/2);
        //start.setTranslateY(GameInformations.WINDOWSIZEY / 2 -start.getHeight()/2);

        playerName = new Button("Enter Player Name");
        playerName.setOnMouseClicked(mouseEvent -> enterPlayerName());
        playerName.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );
        layout.getChildren().add(playerName);
        //playerName.setTranslateX(GameInformations.WINDOWSIZEX / 4-start.getWidth()/2);
        //playerName.setTranslateY(GameInformations.WINDOWSIZEY / 4 -start.getHeight()/2);
        /*highScore = new Button("Hight Score");
        // Загружаем изображение значка "High Score" из ресурсов
        Image highScoreIcon = new Image(getClass().getResourceAsStream("/icons/highscore.png"));
        // Создаем ImageView для установки размеров значка
        ImageView highScoreIconView = new ImageView(highScoreIcon);
        highScoreIconView.setFitWidth(40); // Устанавливаем ширину значка
        highScoreIconView.setFitHeight(40); // Устанавливаем высоту значка

        // Устанавливаем значок в кнопку
        highScore.setGraphic(highScoreIconView);
        // Устанавливаем стиль кнопки: черная обводка
        highScore.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        //highScore.setOnMouseClicked(mouseEvent -> highScore());
        layout.getChildren().add(highScore);


       /* mute = new Button("Mute");
        // Загружаем изображение значка "Mute" из ресурсов
        Image muteIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/mute.png")));
        ImageView muteIconView = new ImageView(muteIcon);
        muteIconView.setFitWidth(40); // Ширина значка
        muteIconView.setFitHeight(40); // Высота значка

        // Устанавливаем изображение значка в качестве графического содержимого кнопки
        mute.setGraphic(new ImageView(muteIcon));
        mute.setOnMouseClicked(mouseEvent -> mute());
        layout.getChildren().add(mute);
        // Устанавливаем стиль кнопки: черная обводка и ширина обводки 2 пикселя
        mute.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

*/

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
        Window.setActive("GameWindow");

    }

    private void enterPlayerName() {
        Window.setActive("EnterPlayerNameWindow");

    }
}
