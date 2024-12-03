//erbt von Animation timer>> handle wir ständig aufgerufen
package at.die4fragezeichen.bullethell;

import at.die4fragezeichen.bullethell.GameObjects.Entity;
import at.die4fragezeichen.bullethell.GameObjects.GamePolygon;
import at.die4fragezeichen.bullethell.GameObjects.Projectile;
import javafx.animation.AnimationTimer;

import javax.xml.transform.Source;

public abstract class GameLoop extends AnimationTimer
{
    long lastTimeCalled = -1; // wann wurde es zuletzt aufgerufen
    int frameCount = 0;
    long sec = 0;
    @Override
    public void handle(long now) // wieviele nanosecunden seit animationtimer aufgruf vergangen sind
    {
        if (lastTimeCalled == -1)
            lastTimeCalled = now;
        long timeSinceFrame = now - lastTimeCalled;

        if ((timeSinceFrame) > GameInformations.REFRESHRATE)
        {
            long diffRefresh = timeSinceFrame-GameInformations.REFRESHRATE;

            doFrame();
            GamePolygon.doFrames();
            Projectile.checkHits();
            Entity.removeEntities();
            GamePolygon.removePolygons();

            lastTimeCalled = now - diffRefresh;
            //testSec(now);

        }
    }
    abstract public void doFrame();

    private void testSec(long now)
    {
        if (frameCount == GameInformations.FPS)
        {
            long diffSek = now-sec;
            System.out.println(frameCount+" Frames: " + diffSek);
            sec = now;
            frameCount = 0;
        }
        frameCount++;
    }
}





