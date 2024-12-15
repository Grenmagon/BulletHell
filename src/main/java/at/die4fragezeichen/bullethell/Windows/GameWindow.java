package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import at.die4fragezeichen.bullethell.GameLoop;
import at.die4fragezeichen.bullethell.GameObjects.Entity;
import at.die4fragezeichen.bullethell.GameObjects.PlayerProjectile;
import at.die4fragezeichen.bullethell.GameObjects.PlayerShip;
import at.die4fragezeichen.bullethell.GameObjects.TestGegner;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWindow extends Window{

    public PlayerShip firstPlayer;
    private GameLoop loop;
    private List<Entity> enemies;

    private int wave = 0;


    public GameWindow(Stage stage, BaseWindow base, String name) {
        super(stage, base, name);
        GameInformations.difficult = GameInformations.Difficult.Easy;
        firstPlayer= new PlayerShip(this);
        double playerTimeAlive = firstPlayer.getSecsAlive();

        enemies= new ArrayList<>();

        loop = new GameLoop() {

            @Override
            public void doFrame() {
                checkEnemies();
                //System.out.println("Enemies"+ enemies.size());
                levelSystem();
            }
        };
    }
    private void levelSystem() {
        if (!enemies.isEmpty())
            return;
        System.out.println("Ich will neue Gegner!!");

        if(GameInformations.difficult == GameInformations.Difficult.Easy) {
            wave++;
            if(wave < 5) {
                for (int i = 0; i < 7; i++) {
                    enemies.add(new TestGegner(this));
                    enemies.get(i).setxKoord(GameInformations.WINDOWSIZEX - 40 * (i + 1));
                    enemies.get(i).setyKoord(40 * (i + 1));
                }
                System.out.println("Das ist Wave: " + wave);
            }
            else if(wave>=5 && wave <8){
                for (int i = 0; i < 4; i++) { // Easy Gegner
                    enemies.add(new TestGegner(this));
                    enemies.get(i).setxKoord(GameInformations.WINDOWSIZEX - 40 * (i + 1));
                    enemies.get(i).setyKoord(40 * (i + 1));
                }
                for(int y= 4;y<8;y++){ //Easy Gegner
                    enemies.add(new TestGegner(this));
                    enemies.get(y).setxKoord(GameInformations.WINDOWSIZEX - 40 * (y+1));
                    enemies.get(y).setyKoord(40 * (8-y));
                }
            }
            else {
                wave = 0;
                System.out.println("Level Easy geschafft ");
                GameInformations.difficult = GameInformations.Difficult.Medium;
                firstPlayer.setShipToDifficulty();
            }


        }
        if(GameInformations.difficult == GameInformations.Difficult.Medium) {
            wave++;
            if(wave<3) {
                // da die Medium Gegner einfügen
                for (int i = 0; i < 3; i++) {
                        enemies.add(new TestGegner(this));
                        enemies.get(i).setxKoord(GameInformations.WINDOWSIZEX - 50 * (i + 1));
                        enemies.get(i).setyKoord(60 * (i + 1));
                }

                }
            else if (wave>=3 && wave<5) {
                    for (int i = 0; i < 4; i++) { // Easy Gegner
                        enemies.add(new TestGegner(this));
                        enemies.get(i).setxKoord(GameInformations.WINDOWSIZEX - 40 * (i + 1));
                        enemies.get(i).setyKoord(40 * (i + 1));
                    }
                    for(int y= 4;y<8;y++){ //Medium Gegner
                        enemies.add(new TestGegner(this));
                        enemies.get(y).setxKoord(GameInformations.WINDOWSIZEX - 40 * (y+1));
                        enemies.get(y).setyKoord(40 * (8-y));
                    }

            } else if (wave >=5 && wave<8) {
                //da die Medium Gegner einfügen
                    for (int i = 0; i < 4; i++) {
                        enemies.add(new TestGegner(this));
                        enemies.get(i).setxKoord(GameInformations.WINDOWSIZEX - 40 * (i + 1));
                        enemies.get(i).setyKoord(40 * (i + 1));
                    }
                    for(int y= 4;y<8;y++){
                        enemies.add(new TestGegner(this));
                        enemies.get(y).setxKoord(GameInformations.WINDOWSIZEX - 40 * (y+1));
                        enemies.get(y).setyKoord(40 * (8-y));
                    }

            }else {
                wave = 0;
                System.out.println("Level Medium geschafft ");
                GameInformations.difficult = GameInformations.Difficult.Hard;
                firstPlayer.setShipToDifficulty();
            }


        }
        if(GameInformations.difficult == GameInformations.Difficult.Hard) {
            if(wave==1){
                enemies.add(new TestGegner(this));
                enemies.get(1).setxKoord(GameInformations.WINDOWSIZEX/2);
                enemies.get(1).setyKoord(40);
            }
            else{
                //Window.setActive(""); // das "Endfenster" einblenden
                System.out.println("Level Hard geschafft ");
            }

        }

    }

    private void checkEnemies()
    {
        Iterator<Entity> iterator = enemies.iterator();
        while (iterator.hasNext())
        {
            Entity e = iterator.next();
            if (e.getRemovePolygon()) {
                iterator.remove();
            }
        }
    }

    @Override
    protected void doActive() {
        loop.start();

    }

    @Override
    public void checkActive() {

    }

    @Override
    protected void doNotActive() {
        loop.stop();
    }
}
