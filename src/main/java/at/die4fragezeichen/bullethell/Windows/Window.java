package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.GameInformations;
import at.die4fragezeichen.bullethell.InputController;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class Window extends Pane
{
    public static List<Window> listeners = new ArrayList<>();
    protected Scene scene;
    protected Stage stage;
    protected String name;
    protected KeyCode activeKey = null;
    private boolean isActive = false;

    public Stage getStage()
    {
        return stage;
    }

    public String getName()
    {
        return name;
    }

    public void setActiveKey(KeyCode activeKey)
    {
        this.activeKey = activeKey;
    }

    public KeyCode getActiveKey()
    {
        return activeKey;
    }

    public boolean isActive()
    {
        return isActive;
    }

    private void addListener(Window signal)
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
    }

    public static void setActive(String name)
    {
        for (Window signal : listeners)
        {
            if (signal.name == name)
                signal.setActive();
        }
    }

    public void setActive()
    {
        isActive = true;
        for (Window signal : listeners)
        {
            if (signal != this)
                signal.setNotActive();
        }

        stage.setScene(scene);
        System.out.println(name + " setActive");
        doActive();
    }

    abstract protected void doActive();

    abstract public void checkActive();

    private void setNotActive()
    {
        isActive = false;
        doNotActive();
        System.out.println(name + " setNotActive");
    }

    abstract protected void doNotActive();
}
