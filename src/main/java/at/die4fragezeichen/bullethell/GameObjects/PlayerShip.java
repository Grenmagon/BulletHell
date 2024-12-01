package at.die4fragezeichen.bullethell.GameObjects;

import at.die4fragezeichen.bullethell.GameInformations;
import at.die4fragezeichen.bullethell.Windows.GameWindow;
import at.die4fragezeichen.bullethell.Windows.Window;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PlayerShip extends Entity{

    public PlayerShip(Pane GameWindow) {
        super(GameWindow, 0);

        if(GameInformations.difficult == GameInformations.Difficult.Easy){
            setLife(50);
            setSpeed(5);
        }else if(GameInformations.difficult == GameInformations.Difficult.Medium){
            setLife(30);
            setSpeed(4);
        } else if (GameInformations.difficult == GameInformations.Difficult.Hard) {
            setLife(10);
            setSpeed(2);
        }
        else
        {
            setLife(1);
            setSpeed(1);
        }

        getPoints().addAll(
                30.0,-100.0, // Koordinate 1 Ecke
                20.0,-70.0,
                10.0,-55.0,
                20.0,-60.0,
                20.0,-40.0,
                0.0,-20.0,
                15.0,-20.0,
                30.0,-30.0,
                45.0,-20.0,
                60.0,-20.0,
                40.0,-40.0,
                40.0,-60.0,
                50.0,-55.0,
                40.0,-70.0,
                30.0,-100.0
                );


        setFill(Color.DARKGREY);
        setStroke(Color.BLACK);
        setxKoord(GameInformations.WINDOWSIZEX-100);
        setyKoord(105);
        moveObject();
        setMovementDegrees(270);
    }



    @Override
    protected void doLoseLife() {

    }

    @Override
    protected void doDeath() {

    }

    @Override
    protected void doFrame() {

    }

    @Override
    protected void doMove() {

    }

    @Override
    protected void doAligment() {

    }

    @Override
    protected void doRemovePolygon() {

    }

    @Override
    protected void doHit(Projectile projectile) {

    }

    @Override
    protected void doWorldCollision() {
        if(getMovementDegrees()==270){
            setMovementDegrees(90);
        }else{
            setMovementDegrees(270);
        }

    }
}
