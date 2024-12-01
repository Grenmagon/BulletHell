package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import at.die4fragezeichen.bullethell.GameLoop;
import at.die4fragezeichen.bullethell.GameObjects.PlayerShip;
import javafx.stage.Stage;

public class GameWindow extends Window{

    public PlayerShip firstPlayer;

    private GameLoop loop;

    public GameWindow(Stage stage, BaseWindow base, String name) {
        super(stage, base, name);
        GameInformations.difficult = GameInformations.Difficult.Hard;
        firstPlayer= new PlayerShip(this);



        loop = new GameLoop() {

            @Override
            public void doFrame() {

            }
        };
    }

    @Override
    protected void doActive() {
        loop.start();

    }

    @Override
    public void checkActive() {

    }

    @Override
    protected void doNotActive() {
        loop.stop();
    }
}
