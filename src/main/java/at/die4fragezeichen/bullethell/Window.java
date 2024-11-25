package at.die4fragezeichen.bullethell;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Window extends GridPane implements WindowSignalHelper
{
    public static List<WindowSignalHelper> listeners = new ArrayList<>();
    private Scene scene;
    private Stage stage;
    private String name;

    public KeyCode activeKey = KeyCode.ESCAPE;

    public void addListener(WindowSignalHelper signal)
    {
        listeners.add(signal);
    }

    public Window(Stage stage, String name)
    {
        this.name = name;
        this.stage = stage;

        scene = new Scene(this, GameInformations.WINDOWSIZEX, GameInformations.WINDOWSIZEY);
        addListener(this);

        scene.setOnKeyPressed(InputController::addPressedKey);
        scene.setOnKeyReleased(InputController::removePressedKey);
        init();
    }


    Button b;
    Label l;
    public void init()
    {
        b = new Button("PRESSME");
        add(b,1, 0);
        b.setOnMouseClicked(event ->bClicked());

        l = new Label("Bla");
        add(l,0,0);
    }


    private void bClicked()
    {
        l.setText("Clicked");
    }

    @Override
    public void checkActive()
    {
        if (InputController.getPressedKeys().contains(activeKey))
            setActive();
    }

    @Override
    public void setActive()
    {
        for (WindowSignalHelper signal: listeners)
            signal.setNotActive();

        stage.setScene(scene);
        stage.setTitle(name);
        System.out.println(name + " wird aktiv");
    }

    @Override
    public void setNotActive()
    {
        System.out.println(name + " nicht mehr aktiv");
    }
}
