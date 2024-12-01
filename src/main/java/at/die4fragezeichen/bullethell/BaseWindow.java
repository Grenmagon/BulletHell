// Fenster werden definiert
package at.die4fragezeichen.bullethell;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseWindow extends Application
{


    private Scene scene;
// Objekte werden erzeugt für alle Windows, welche benötigt werden
    //public MenuTest mt;


    // start muss implementiert werden, Stage ist die höchste Ebene
    @Override
    public void start(Stage stage) throws IOException
    {
        //mt = new MenuTest(stage, this, "Menu");
        //mt.setActive(); // Szene wird aktiv geschalten
        stage.show();
    }

// mit Launch wird der Startbedingung gestartet
    public static void go(String[] args)
    {
        launch();
    }
}