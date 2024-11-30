package at.die4fragezeichen.bullethell;

import at.die4fragezeichen.bullethell.GameObjects.Entity;
import at.die4fragezeichen.bullethell.GameObjects.GamePolygon;
import at.die4fragezeichen.bullethell.GameObjects.Projectile;
import javafx.animation.AnimationTimer;

public abstract class GameLoop extends AnimationTimer
{
    long lastTimeCalled = 0;

    @Override
    public void handle(long now)
    {
        if (now - lastTimeCalled > GameInformations.REFRESHRATE)
        {
            doFrame();
            GamePolygon.doFrames();
            Projectile.checkHits();
            Entity.removeEntities();
            GamePolygon.removePolygons();
            lastTimeCalled = now;
        }
    }
    abstract public void doFrame();
}





