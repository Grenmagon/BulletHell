package at.die4fragezeichen.bullethell.GameObjects;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Entity extends GamePolygon
{
    private int life = 0;

    public static List<Entity> entities = new ArrayList<>();
    public static void removeEntities()
    {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext())
        {
            Entity e = iterator.next();
            if (e.getRemovePolygon())
            {
                e.removeEntity(iterator);
            }
        }
    }

    public Entity(Pane pane, int life)
    {
        super(pane);
        this.life = life;
        entities.add(this);
    }

    private void removeEntity(Iterator<Entity> iterator)
    {
        iterator.remove();
    }


    public int getLife()
    {
        return life;
    }

    public void setLoseLife(Projectile p)
    {
        int damage = p.getDamage();
        doLoseLife();
        System.out.print("Ich bin getroffen von " + p.getSpawner().id + " und hab " + getLife());
        life -= damage;
        System.out.println(" und habe noch " + getLife());
        if (life <= 0)
            setDeath();
    }
    abstract protected void doLoseLife();

    private void setDeath()
    {
        doDeath();
        setRemovePolygon();
    }
    abstract protected void doDeath();

}
