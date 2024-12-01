//Gegner und Spieler
package at.die4fragezeichen.bullethell.GameObjects;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Entity extends GamePolygon
{
    private int life = 0; // Lebenspunkte

    public static List<Entity> entities = new ArrayList<>(); // statische Liste

    // Objekte werden überall entfernt wenn sie Sterben
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

    //Leben gleich beim erstellen einstellen
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


    public void setLife(int life)
    {
        this.life = life;
    }
    public int getLife()
    {
        return life;
    }

    // Methode, wenn Projektil mich trifft
    public void setLoseLife(Projectile p)
    {
        int damage = p.getDamage();
        doLoseLife();
        System.out.print("Ich bin getroffen von " + p.getSpawner().id + " und hab " + getLife());
        life -= damage;
        System.out.println(" und habe noch " + getLife());
        if (life <= 0)
            setDeath(); // entity ist gestorben
    }
    // was soll getan werden wenn ich leben verliere?
    abstract protected void doLoseLife();

    // wenn ich gestorben bin entferne mich aus den Listen etc. und mache noch die in doDeath definierten Sachen
    private void setDeath()
    {
        doDeath();
        setRemovePolygon();
    }
    // was soll getan werden wenn ich sterbe?
    abstract protected void doDeath();

}
