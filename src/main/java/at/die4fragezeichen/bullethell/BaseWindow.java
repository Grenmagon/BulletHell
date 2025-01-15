// Fenster werden definiert
package at.die4fragezeichen.bullethell;


import at.die4fragezeichen.bullethell.Windows.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;

public class BaseWindow extends Application {


    private Scene scene;
    // Objekte werden erzeugt für alle Windows, welche benötigt werden
    //public MenuTest mt;
    public StartWindow startWindow;
    public GameWindow gameWindow;
    public EnterPlayerNameWindow enterPlayerNameWindow;
    public FinalWindow finalWindow;
    public DifficultySelectionWindow difficultySelectionWindow;


    // start muss implementiert werden, Stage ist die höchste Ebene
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/Horizon.otf"), 20);
            System.out.println("Font Horizon successfully loaded.");
        } catch (Exception e) {
            System.err.println("Failed to load font: " + e.getMessage());
        }
        //mt = new MenuTest(stage, this, "Menu");
        //mt.setActive(); // Szene wird aktiv geschalten
        startWindow = new StartWindow(stage, this, "StartWindow");
        difficultySelectionWindow = new DifficultySelectionWindow(stage,this, "DifficultySelectionWindow");
        enterPlayerNameWindow = new EnterPlayerNameWindow(stage, this, "EnterPlayerNameWindow");
        gameWindow = new GameWindow(stage, this, "GameWindow");
        finalWindow = new FinalWindow(stage,this,"FinalWindow");
        //gameWindow.setActive();
        startWindow.setActive();
        stage.show();
    }

    // mit Launch wird der Startbedingung gestartet
    public static void go(String[] args) {
        launch();
    }
}