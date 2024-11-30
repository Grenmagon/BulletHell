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

    public double getxKoord()
    {
        return xKoord;
    }

    public void setxKoord(double xKoord)
    {
        this.xKoord = xKoord;
    }

    public double getyKoord()
    {
        return yKoord;
    }

    public void setyKoord(double yKoord)
    {
        this.yKoord = yKoord;
    }

    public double getAlignmentDegrees()
    {
        return alignmentDegrees;
    }

    public void setAlignmentDegrees(double alignmentDegrees)
    {
        this.alignmentDegrees = alignmentDegrees;
    }

    public double getMovementDegrees()
    {
        return movementDegrees;
    }

    public void setMovementDegrees(double movementDegrees)
    {
        this.movementDegrees = movementDegrees;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
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
        calculateMove();
        moveObject();
    }

    private void calculateMove()
    {
        double[] coord = new double[2];

        if (GameInformations.calculateKoord(getMovementDegrees(), getSpeed(), coord) == 0)
        {
            setxKoord(getxKoord() - coord[0]);
            setyKoord(getyKoord() + coord[1]);
        }
    }

    protected void moveObject()
    {
        setTranslateX(xKoord);
        setTranslateY(yKoord);
    }


    //Zum einstellen des Vecotors für die Bewegung im Frame
    abstract protected void doMove();

    public double getSecsAlive()
    {
        return (double) framesAlive / GameInformations.FPS;
    }

    /*
    TODO Was machen alles Polygon (Player + Gegner)
    spawn projectil
     */


}
