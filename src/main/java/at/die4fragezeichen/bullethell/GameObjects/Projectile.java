
package at.die4fragezeichen.bullethell.GameObjects;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//erben von GamePolygon
public abstract class Projectile extends GamePolygon
{
    private int damage = 0; // Schaden welcher vom Projektil erzeug wird
    private GamePolygon spawner; // wer hat den Schuss abgefeuert
    public static List<Projectile> projectiles = new ArrayList<>(); // statische Liste die alle Projektile auflistet

// richtung und geschwindigkeit die das Geschoss haben soll- wird beim erzeugen des Porjektils eingestellt
    public Projectile(GamePolygon spawner, double direction, double speed)
    {
        super((Pane)spawner.getParent());
        this.spawner = spawner;
        setSpeed(speed);
        setMovementDegrees(direction);
        setAlignmentDegrees(direction);
        setAligment();
        projectiles.add(this);

        // projektil wird Mittig von dem Objekt welches das Projektil abgefeuert hat gespawnt
        Bounds spawnBounds = spawner.getBoundsInParent();
        setxKoord((spawnBounds.getMinX()+spawnBounds.getMaxX())/2);
        setyKoord((spawnBounds.getMinY()+spawnBounds.getMaxY())/2);
        moveObject();
    }

    // geht alle Projektile durch und alle Polygone und checkt ob ein Projektil ein Polygon trifft
    public static void checkHits()
    {
        for (Projectile p: projectiles)
        {
            for (GamePolygon gp: GamePolygon.polygons)
            {
                if (p.getBoundsInParent().intersects( gp.getBoundsInParent() ))
                {
                    gp.setHit(p); // es muss definiert werden in doHit ob mich der treffer interessiert und was getan werden muss wenn getrofen wurde

                }
            }
        }
    }


    public int getDamage()
    {
        return damage;
    }
    public void setDamage(int damage)
    {
        this.damage = damage;
    }
    public GamePolygon getSpawner()
    {
        return spawner;
    }
    public void setSpawner(GamePolygon spawner)
    {
        this.spawner = spawner;
    }

    // abtrakte Methoden
    @Override
    protected void doFrame()
    {

    }

    @Override
    protected void doMove()
    {

    }

    @Override
    protected void doAligment()
    {

    }

    @Override
    protected void doRemovePolygon()
    {
        System.out.println("Remove Projectile");
        projectiles.remove(this);
    }

    // was muss passieren wenn Projektil was trifft
    @Override
    protected void doHit(Projectile projectile)
    {

    }

    // wenn Projektil mit der Außenwelt kollidiert wird es entfernt
    @Override
    protected void doWorldCollision()
    {
        setRemovePolygon();
    }

    // was möchte das Projektil machen, wenn es bereits getroffen hat- removen oder soll es weiter gehen ?
    abstract protected void setHitEntity(GamePolygon polygon);



}
