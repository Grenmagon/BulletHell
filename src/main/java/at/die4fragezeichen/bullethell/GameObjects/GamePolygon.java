//erbt von Polygon dies ist ein Objekt/ eine Form die anhand von Koordinaten Objekte zeichnet
// diese Klasse hat generelle Methoden die die Objekte haben sollen und erzeugt die Liste aller Objekte
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
    // wo ist das Objekt gerade, wo soll es hin und wie soll es dorthin gelangen
    private double xKoord = 0;
    private double yKoord = 0;
    private double alignmentDegrees = 0;
    private double movementDegrees = 0;
    private double speed = 0;
    private long framesAlive = 0;
    private boolean removePolygon = false;

    public int id = 0;

    public static List<GamePolygon> polygons = new ArrayList<>(); // statische Liste für alle Objekte im Spiel
    private List<Projectile> projectileHits = new ArrayList<>();

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
// Konstruktor muss eine Pane übergeben werden zum Bsp das Spielfenster
    public GamePolygon(Pane pane)
    {
        addToPane(pane);
        polygons.add(this);

        id = (int) GameInformations.getRandomInRange(1000,9999); // "eindeutige" ID wird zugewiesen
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

    // fügt sich selbst zur Ebene hinzu
    private void addToPane(Pane parentPane)
    {
        parentPane.getChildren().add(this);
    }

    //wir automatisch jedes Frame aufgerufen
    public void setFrame()
    {
        doFrame(); // was wird getan
        setMove();
        checkWorldCollision(); // werden die Wände vom Fenster getroffen
        framesAlive++;

        Iterator<Projectile> it = projectileHits.iterator();
        while (it.hasNext())
        {
            Projectile p = it.next();
            if (p.getRemovePolygon())
                it.remove();
        }
    }

    //Was pro Frame gemacht werden soll (ausser movement)
    abstract protected void doFrame();
// wenn Bewegung gemacht werden soll
    private void setMove()
    {
        doMove(); // wie möchte ich mich bewegen
        calculateMove();
        moveObject();  // setzt das Object zu den erechneten Koordinaten
    }
    // es wird berechnet wie die x und y Koordinaten gesetzt werden müssen anhand der eingestellten move Parameter
    private void calculateMove()
    {
        double[] coord = new double[2];

        double frameSpeed = getSpeed()/GameInformations.FPS;
        if (GameInformations.calculateKoord(getMovementDegrees(), frameSpeed, coord) == 0)
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
// Ausrichtung von Objekt wird gesetzt
    public void setAligment()
    {
        doAligment();
        setRotate(getAlignmentDegrees() + 180);
    }
    // was soll passierren wenns gedreht ist?
    abstract protected void doAligment();

// rechnet aus wieviele Sekunden Frame aktiv ist
    public double getSecsAlive()
    {
        return (double) framesAlive / GameInformations.FPS;
    }
// soll Polgon entfent werden?
    public boolean getRemovePolygon()
    {
        return removePolygon;
    }
    protected void setRemovePolygon()
    {
        removePolygon = true;
    }

    //iteratoren sind nötig um das removen geordnet und richtig durchzuführen, Polygon wird entfernt
    private void removePolygon(Iterator<GamePolygon> iterator)
    {
        doRemovePolygon(); // soll Objekt was tun wenn es removed wird
        Pane parent = (Pane) getParent(); // wird aus ebene entfernt
        parent.getChildren().remove(this);
        iterator.remove(); // wird aus allen listen entfernt
    }
    abstract protected void doRemovePolygon();

    //Wenn von einem Projectil getroffen
    protected void setHit(Projectile projectile)
    {
        if (projectileHits.contains(projectile))
            return;
        doHit(projectile);
        projectileHits.add(projectile);
    }
    abstract protected void doHit(Projectile projectile);
// checkt, wird das Fenster verlassen ?
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

    // checkt ob Welt verlassen wird und ruft die dazu passende Methode auf
    private void checkWorldCollision()
    {
        if (leaveParent())
            doWorldCollision();
    }
    abstract protected void doWorldCollision();



}
