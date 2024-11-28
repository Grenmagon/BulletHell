package at.die4fragezeichen.bullethell.GameObjects;

import at.die4fragezeichen.bullethell.GameInformations;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

abstract class GamePolygon extends Polygon
{
    private double xKoord = 0;
    private double yKoord = 0;
    private double alignmentDegrees = 0;
    private double movementDegrees = 0;
    private double speed = 0;
    private long framesAlive = 0;

    public static List<GamePolygon> polygons = new ArrayList<>();

    public GamePolygon(Pane pane)
    {
        addToPane(pane);
        polygons.add(this);
    }

    private void addToPane(Pane parentPane)
    {
        parentPane.getChildren().add(this);
    }

    public void setFrame()
    {
        doFrame();
        setMove();
        framesAlive++;
    }

    //Was pro Frame gemacht werden soll (ausser movement)
    abstract protected void doFrame();

    private void setMove()
    {
        doMove();
        //TODO das bewegen im Frame selbst
    }

    //Zum einstellen des Vecotors für die Bewegung im Frame
    abstract protected void doMove();

    public double getSecsAlive()
    {
        return (double) framesAlive / GameInformations.FPS;
    }

    /*
    TODO Was machen alles Polygon (Player + Gegner)
    Move
    CalcMove
    spawn projectil
     */


}
