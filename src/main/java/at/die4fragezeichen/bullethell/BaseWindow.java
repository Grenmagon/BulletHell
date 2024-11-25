package at.die4fragezeichen.bullethell;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseWindow extends Application
{


    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        Window w = new Window(stage, "Menu");
        w.activeKey = KeyCode.ESCAPE;
        Window ww = new Window(stage, "Game");
        ww.activeKey = KeyCode.NUMPAD1;
        w.setActive();
        //ww.setActive();
        stage.show();
    }

    public static void go(String[] args)
    {
        launch();
    }
}