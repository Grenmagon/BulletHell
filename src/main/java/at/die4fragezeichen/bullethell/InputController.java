//checkt welche Tasten sind gedrückt derzeit
package at.die4fragezeichen.bullethell;

import at.die4fragezeichen.bullethell.Windows.Window;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class InputController
{
    private static final Set<KeyCode> pressedKeys = new HashSet<>(); // statische Liste, welche Tasten sind derzeit gedrückt

    //wenn Key gedrückt wird>> fügt die Taste der Liste hinzu und Fensterobjekt werden durchgegangen, checkaktive wird gemeldet, dass eine gewisse Taste gedrückt wurde.
    public static void addPressedKey(KeyEvent event)
    {
        pressedKeys.add(event.getCode());

        for(Window signal: Window.listeners)
            signal.checkActive();
    }
// wenn Key losgelassen wird>> Taste wird aus Liste entfernt
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
    public static void resetPressedKeys()
    {
        pressedKeys.remove(KeyCode.SPACE);
        pressedKeys.remove(KeyCode.A);
        pressedKeys.remove(KeyCode.D);
    }
}
