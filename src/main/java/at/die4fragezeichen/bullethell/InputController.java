package at.die4fragezeichen.bullethell;

import at.die4fragezeichen.bullethell.Windows.Window;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class InputController
{
    private static final Set<KeyCode> pressedKeys = new HashSet<>();

    public static void addPressedKey(KeyEvent event)
    {
        pressedKeys.add(event.getCode());

        for(Window signal: Window.listeners)
            signal.checkActive();
    }

    public static void removePressedKey(KeyEvent event)
    {
        pressedKeys.remove(event.getCode());
    }

    public static Set<KeyCode> getPressedKeys()
    {
        return pressedKeys;
    }

    public static void writePressedKeys()
    {
        for (KeyCode k: pressedKeys)
        {
            System.out.println(k);
        }
    }
}
