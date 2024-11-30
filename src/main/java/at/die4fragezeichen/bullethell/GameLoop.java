package at.die4fragezeichen.bullethell;

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
            lastTimeCalled = now;
        }
    }
    abstract public void doFrame();
}





