package at.die4fragezeichen.bullethell;

public class GameInformations
{
    public static final double WINDOWSIZEX = 480;
    public static final double WINDOWSIZEY = 480;

    public static final int SEC = 1_000_000_000;
    public static final int FPS = 60;
    public static final int REFRESHRATE = SEC / FPS;

    public static int calculateKoord(double degree, double r, double[] coord)
    {
        r = -r; //Sonst gehts rückwärts!!!!

        if (coord.length != 2)
            return 1;

        double bogenmas = degree * (Math.PI / 180);
        if (degree % 180 != 0)
            coord[0] = r * Math.sin(bogenmas);
        else
            coord[0] = 0;
        if ((degree + 90) % 180 != 0)
            coord[1] = r * Math.cos(bogenmas);
        else
            coord[1] = 0;
        return 0;
    }
}
