package at.die4fragezeichen.bullethell.GameObjects;

import at.die4fragezeichen.bullethell.GameInformations;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TestGegner extends Entity {

    private double sinceMoveChange = 0;
    private double sinceShot = 0;
    private double timeSinceHit = 0;

    public TestGegner(Pane pane) {
        super(pane, 5);
        getPoints().addAll(

                //ursprüngl. Koordinaten -> Pfeile (Gegner) schauen nach oben
                0.0, -10.0,
                -10.0, 10.0,
                0.0, 5.0,
                10.0, 10.0


                /* Um 90° nach links gedreht

                10.0, 10.0,
                5.0, 0.0,
                10.0, -10.0,
                -10.0, 0.0

                */

        );
        setFill(Color.BLUE);
        setStroke(Color.BLACK); // Farbe für den Rahmen bzw. das Wireframe

        setxKoord(GameInformations.WINDOWSIZEX/2); // Position Gegner auf X-Achse (Mitte weil /2)
        setyKoord(GameInformations.WINDOWSIZEY/2); // Position Gegner auf Y-Achse (Mitte weil /2)
        moveObject(); // dadurch setze ich Objekt auf entspr. Koordinaten, die gesetzt sind

        setMovementDegrees(90); // Objekt bewegt sich nach rechts
        setAlignmentDegrees(getMovementDegrees()); //Raumschiff soll sich in Movementrichtung drehen
        setAligment(); // Raumschiff dreht sich aktiv
        if (GameInformations.difficult == GameInformations.Difficult.Easy) { // GameInformations enthält alle relevanten Infos
            setSpeed(50);
        } else {
            setSpeed(150);
        }
    }

    @Override
    protected void doLoseLife() {
        // eher dazu verwenden, um bspw. Raumschiff blinken zu lassen, wenn es getroffen wird
        setFill(Color.BLACK);
        timeSinceHit = getSecsAlive();

        if (getLife() > 0) {
            System.out.println("Mayday! Mayday! Ich bin getroffen! Ich habe noch: " + getLife() + " Leben!");
        } else {
            System.out.println("AAAARRRGH!!");
        }
    }

    @Override
    protected void doDeath() {
        // kann ignoriert werden
    }

    @Override
    protected void doFrame() {
        /* wird jedes Frame aufgerufen
        idF wird alle 3 sec ein Schuss pro Objekt abgegeben - kann mit Schwierigkeit getunet werden

           if (getSecsAlive() % 3 == 0) {
            PlayerProjectile e = new PlayerProjectile(this, 180, 50);
        }
         */
        if (getSecsAlive() - sinceShot > 3) {
            PlayerProjectile e = new PlayerProjectile(this, 180, 50);
            sinceShot = getSecsAlive();
        }
    }

    @Override
    protected void doMove() { // wird jedes Frame aufgerufen
        if (getSecsAlive()-sinceMoveChange >= 0.5) {
            setMovementDegrees(getMovementDegrees()+ 45.0);
            if (getMovementDegrees() >= 360) {
                setMovementDegrees(getMovementDegrees()-360);
            }
            sinceMoveChange = getSecsAlive();

            setAlignmentDegrees(getMovementDegrees()); // Alignment des Raumschiff wird in Bewegungsrichtung des Raumschiffs ausgerichtet

            setAligment();
        }

    }

    @Override
    protected void doAligment() {

    }

    @Override
    protected void doRemovePolygon() {
        // wird kurz bevor Polyg. aus Speicher entfernt wird aufgerufen
    }

    @Override
    protected void doHit(Projectile projectile) {
        if (projectile.getSpawner().getClass() != this.getClass()) {
            setLoseLife(projectile);
        }
    }

    @Override
    protected void doWorldCollision() {
        if (leaveLeft()) {
            setxKoord(getBoundsInParent().getWidth()/2);
            // setMovementDegrees(90);
        }

        if (leaveRight()) {
            setxKoord(GameInformations.WINDOWSIZEX-getBoundsInParent().getWidth()/2);
            // setMovementDegrees(270);
        }

        if (leaveUp()) {
            setyKoord(getBoundsInLocal().getHeight()/2);
        }

        if (leaveDown()) {
            setyKoord(GameInformations.WINDOWSIZEY-getBoundsInParent().getHeight()/2);
        }

    }
}
