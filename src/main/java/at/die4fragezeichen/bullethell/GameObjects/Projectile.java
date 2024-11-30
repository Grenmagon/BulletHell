package at.die4fragezeichen.bullethell.GameObjects;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Projectile extends GamePolygon
{
    private int damage = 0;
    private GamePolygon spawner;
    public static List<Projectile> projectiles = new ArrayList<>();


    public Projectile(GamePolygon spawner, double direction, double speed)
    {
        super((Pane)spawner.getParent());
        this.spawner = spawner;
        setSpeed(speed);
        setMovementDegrees(direction);
        setAlignmentDegrees(direction);
        setAligment();
        projectiles.add(this);

        Bounds spawnBounds = spawner.getBoundsInParent();
        setxKoord((spawnBounds.getMinX()+spawnBounds.getMaxX())/2);
        setyKoord((spawnBounds.getMinY()+spawnBounds.getMaxY())/2);
        moveObject();
    }

    public static void checkHits()
    {
        for (Projectile p: projectiles)
        {
            for (GamePolygon gp: GamePolygon.polygons)
            {
                if (p.getBoundsInParent().intersects( gp.getBoundsInParent() ))
                {
                    gp.doHit(p);

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

    @Override
    protected void doHit(Projectile projectile)
    {

    }

    @Override
    protected void doWorldCollision()
    {
        setRemovePolygon();
    }

    abstract protected void setHitEntity(GamePolygon polygon);



}
