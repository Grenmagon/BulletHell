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

    public MenuTest mt;
    public GameTest gt;

    @Override
    public void start(Stage stage) throws IOException
    {
        mt = new MenuTest(stage, this, "Menu");
        gt = new GameTest(stage, this, "Game");
        mt.setActive();
        stage.show();
    }

    public static void go(String[] args)
    {
        launch();
    }
}