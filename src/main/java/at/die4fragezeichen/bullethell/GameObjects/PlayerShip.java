package at.die4fragezeichen.bullethell.GameObjects;

import at.die4fragezeichen.bullethell.GameInformations;
import at.die4fragezeichen.bullethell.InputController;
import at.die4fragezeichen.bullethell.Windows.GameWindow;
import at.die4fragezeichen.bullethell.Windows.Window;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PlayerShip extends Entity{
    private double baseSpeed = 0;

    private double timeSinceShot = 0;
    private final double FIRERATE = 0.5;
    private final double HITREGTIME = 0.2;
    private double timeSinceHit =0;

    public PlayerShip(Pane GameWindow) {
        super(GameWindow, 0);

        setShipToDifficulty();

        baseSpeed=getSpeed();


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
        setyKoord(GameInformations.WINDOWSIZEY);
        moveObject();

    }

    public void setShipToDifficulty() {
        if(GameInformations.difficult == GameInformations.Difficult.Easy){
            setLife(35);
            setSpeed(500);
        }else if(GameInformations.difficult == GameInformations.Difficult.Medium){
            setLife(20);
            setSpeed(350);
        } else if (GameInformations.difficult == GameInformations.Difficult.Hard) {
            setLife(10);
            setSpeed(200);
        }
        else
        {
            setLife(1);
            setSpeed(1);
        }
    }


    @Override
    protected void doLoseLife() {
        // für 1 Sekunde auf Rot stellen
        setFill(Color.RED);
        timeSinceHit=getSecsAlive();

        System.out.println("ich habe ein Leben verloren" + getLife());
    }

    @Override
    protected void doDeath() {



    }

    @Override
    protected void doFrame() {

        if ( InputController.getPressedKeys().contains(KeyCode.SPACE))
        {
            if (getSecsAlive() - timeSinceShot > FIRERATE) {// Timer für verzögerung zwischen schüssen
                PlayerProjectile pp = new PlayerProjectile(this, 0, 200);
                timeSinceShot = getSecsAlive();
            }
        }
        if(getSecsAlive()-timeSinceHit>HITREGTIME){
            setFill(Color.DARKGREY);
        }

    }

    @Override
    protected void doMove() {
        if ( InputController.getPressedKeys().contains(KeyCode.A)&& InputController.getPressedKeys().contains(KeyCode.D)){
            setSpeed(0);
        }
        else if ( InputController.getPressedKeys().contains(KeyCode.A)){
            setMovementDegrees(270);
            setSpeed(baseSpeed);

        }
        else if( InputController.getPressedKeys().contains(KeyCode.D)){
            setMovementDegrees(90);
            setSpeed(baseSpeed);
        }else {
            setSpeed(0);
        }

    }

    @Override
    protected void doAligment() {

    }

    @Override
    protected void doRemovePolygon() {
        //Welches auch immer, vermutlich Highscore anzeige und neues Start Menü
        System.out.println("Highscore: " +GameInformations.highscore);
        Window.setActive("StartWindow"); // das "Endfenster" einblenden

    }

    @Override
    protected void doHit(Projectile projectile) {
        if(projectile.getSpawner()==this){
            return;
        }
        System.out.println("DAs Schiff wurde getroffen");
        setLoseLife(projectile);
        projectile.setHitEntity(this);



    }

    @Override
    protected void doWorldCollision() {
        if (leaveRight())
        {
            double toMuch = getBoundsInParent().getMaxX() - ((Pane) getParent()).getWidth();
            setxKoord(getxKoord() - toMuch);
        }
        else if (leaveLeft())
        {
            double toMuch = getBoundsInParent().getMinX();
            setxKoord(getxKoord() - toMuch);
        }

        moveObject();

    }

}
