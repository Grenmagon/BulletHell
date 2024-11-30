package at.die4fragezeichen.bullethell.GameObjects;

import at.die4fragezeichen.bullethell.GameInformations;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class GamePolygon extends Polygon
{
    private double xKoord = 0;
    private double yKoord = 0;
    private double alignmentDegrees = 0;
    private double movementDegrees = 0;
    private double speed = 0;
    private long framesAlive = 0;
    private boolean removePolygon = false;

    public int id = 0;

    public static List<GamePolygon> polygons = new ArrayList<>();

    public static void doFrames()
    {
        for (int i = 0; i < polygons.size(); i++)
        {
            polygons.get(i).setFrame();
        }

    }

    public static void removePolygons()
    {
        Iterator<GamePolygon> iterator = polygons.iterator();
        while (iterator.hasNext())
        {
            GamePolygon gp = iterator.next();
            if (gp.removePolygon)
            {
                gp.removePolygon(iterator);
            }
        }
    }

    public GamePolygon(Pane pane)
    {
        addToPane(pane);
        polygons.add(this);

        id = (int) GameInformations.getRandomInRange(1000,9999);
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
        checkWorldCollision();
        framesAlive++;
    }
    //Was pro Frame gemacht werden soll (ausser movement)
    abstract protected void doFrame();

    private void setMove()
    {
        doMove();
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

    public void setAligment()
    {
        doAligment();
        setRotate(getAlignmentDegrees());
    }
    abstract protected void doAligment();

    public double getSecsAlive()
    {
        return (double) framesAlive / GameInformations.FPS;
    }

    protected boolean getRemovePolygon()
    {
        return removePolygon;
    }
    protected void setRemovePolygon()
    {
        removePolygon = true;
    }

    private void removePolygon(Iterator<GamePolygon> iterator)
    {
        doRemovePolygon();
        Pane parent = (Pane) getParent();
        parent.getChildren().remove(this);
        //polygons.remove(this);
        iterator.remove();
    }
    abstract protected void doRemovePolygon();

    //Wenn von einem Projectil getroffen
    abstract protected void doHit(Projectile projectile);

    protected boolean leaveParent()
    {
        return leaveRight() || leaveLeft() || leaveUp() ||leaveDown();
    }
    protected boolean leaveRight()
    {
        return getBoundsInParent().getMaxX() > ((Pane) getParent()).getWidth();
    }
    protected boolean leaveLeft()
    {
        return getBoundsInParent().getMinX() < 0;
    }
    protected boolean leaveUp()
    {
        return getBoundsInParent().getMinY() < 0;
    }
    protected boolean leaveDown()
    {
        return getBoundsInParent().getMaxY() > ((Pane) getParent()).getHeight();
    }

    private void checkWorldCollision()
    {
        Bounds parent = getParent().getBoundsInParent();
        Bounds bounds = getBoundsInParent();
        //if (bounds.intersects(parent))
        if (leaveParent())
            doWorldCollision();
    }
    abstract protected void doWorldCollision();



}
