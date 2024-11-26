package at.die4fragezeichen.bullethell;

import at.die4fragezeichen.bullethell.Windows.GameTest;
import at.die4fragezeichen.bullethell.Windows.MenuTest;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseWindow extends Application
{


    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        MenuTest w = new MenuTest(stage, "Menu");
        GameTest ww = new GameTest(stage, "Game");
        w.setActive();
        stage.show();
    }

    public static void go(String[] args)
    {
        launch();
    }
}