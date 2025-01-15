package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DifficultySelectionWindow extends Window{
    private Button easy;
    private Button medium;
    private Button hard;



    public DifficultySelectionWindow(Stage stage, BaseWindow base, String name) {
        super(stage, base, name);
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);// Выравниваем содержимое по центру
        layout.setPrefSize(GameInformations.WINDOWSIZEX, GameInformations.WINDOWSIZEY);


        easy = new Button("easy");
        easy.setOnMouseClicked(mouseEvent -> setEasy());
        easy.setMinWidth(210);
        easy.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );

        medium = new Button("medium");
        medium.setOnMouseClicked(mouseEvent -> setMedium());
        medium.setMinWidth(210);
        medium.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );
        hard = new Button("hard");
        hard.setOnMouseClicked(mouseEvent -> setHard());
        hard.setMinWidth(210);
        hard.setStyle("-fx-font-family: 'Horizon'; " +
                "-fx-font-size: 20px; " +
                "-fx-border-color: black; " +      // Чёрная обводка
                "-fx-border-width: 2px;"          // Толщина обводки
        );

        layout.getChildren().addAll(easy, medium, hard);
        this.getChildren().add(layout);

    }

    private void startGame(){
        Window.setActive("GameWindow");
    }

    private void setEasy(){
        GameInformations.difficult = GameInformations.Difficult.Easy;
        startGame();
    };

    private void setMedium(){
        GameInformations.difficult = GameInformations.Difficult.Medium;
        startGame();
    };

    private void setHard(){
        GameInformations.difficult = GameInformations.Difficult.Hard;
        startGame();
    };

    @Override
    protected void doActive() {

    }

    @Override
    public void checkActive() {

    }

    @Override
    protected void doNotActive() {

    }
}
