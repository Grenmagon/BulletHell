// Methoden für die einzelnen Windows werden implementiert
package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import at.die4fragezeichen.bullethell.InputController;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
//in den Szenen sind die sogenannten Panes >> in diesen können weitere Panes sein und z.B.: Buttons etc.
public abstract class Window extends Pane
{
    public static List<Window> listeners = new ArrayList<>(); // statische Liste für die Windows >>> windows werden automatisch dieser Liste hinzugefügt
    private Scene scene;
    private Stage stage;
    private String name;
    private KeyCode activeKey = null; // Tasten können defineirt werden die dann aktionen setzen, wenn sie grdrückt werden
    private boolean isActive = false;
    private BaseWindow base; //Zugriff auf vorhandene Variablen, um diese zu verändern


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

    public BaseWindow getBase()
    {
        return base;
    }

    private void addListener(Window signal)
    {
        listeners.add(signal);
    }

    // Konstruktor >> Name muss eindeutig sein
    public Window(Stage stage, BaseWindow base, String name)
    {
        this.name = name;
        this.stage = stage;
        this.base = base;

        scene = new Scene(this, GameInformations.WINDOWSIZEX, GameInformations.WINDOWSIZEY); //neues Objekt Szene wird eine Pane hinzugefügt
        addListener(this);

        scene.setOnKeyPressed(InputController::addPressedKey); //es wird die Klasse aufgerufen, welche darauf hört ob Taste gedrückt wird bzw. losgelassen wird
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

    // Setzt die Fenster aktiv, welche aktiv sein sollen, per Definition
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

    // Abstrakte Methode, muss ausprogrammiert werden, es muss definiert werden wann die Fenster aktiv werden und was passiert
    abstract protected void doActive();

// in check aktive wird angegeben, was passiert wenn eine gewisse Taste gedrückt wurde
    abstract public void checkActive();

    private void setNotActive()
    {
        isActive = false;
        doNotActive();
        System.out.println(name + " setNotActive");
    }

    abstract protected void doNotActive();
}
