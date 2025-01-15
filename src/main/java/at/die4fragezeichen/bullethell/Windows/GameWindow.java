package at.die4fragezeichen.bullethell.Windows;

import at.die4fragezeichen.bullethell.BaseWindow;
import at.die4fragezeichen.bullethell.GameInformations;
import at.die4fragezeichen.bullethell.GameLoop;
import at.die4fragezeichen.bullethell.GameObjects.Entity;
import at.die4fragezeichen.bullethell.GameObjects.PlayerProjectile;
import at.die4fragezeichen.bullethell.GameObjects.PlayerShip;
import at.die4fragezeichen.bullethell.GameObjects.TestGegner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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

        //highscore Label
        VBox layoutHighscore = new VBox();
        layoutHighscore.setAlignment(Pos.TOP_RIGHT);
        layoutHighscore.setPadding(new Insets(20));

        layoutHighscore.setPrefWidth(GameInformations.WINDOWSIZEX);
        layoutHighscore.setPrefHeight(GameInformations.WINDOWSIZEY);

        Label highscoreLabel = new Label("Highscore: "+GameInformations.highscore);
        highscoreLabel.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 20px;");

        layoutHighscore.getChildren().add(highscoreLabel);
        this.getChildren().add(layoutHighscore);

        //playername Label
        VBox layoutPlayername= new VBox();
        layoutPlayername.setAlignment(Pos.BOTTOM_RIGHT);

        layoutPlayername.setPrefWidth(GameInformations.WINDOWSIZEX);
        layoutPlayername.setPrefHeight(GameInformations.WINDOWSIZEY);

        Label playernameLabel = new Label("playername: " +GameInformations.playername);
        playernameLabel.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 20px;");
        playernameLabel.setMinWidth(210);

        layoutPlayername.getChildren().add(playernameLabel);
        this.getChildren().add(layoutPlayername);

        //wave and Level Label
        VBox layoutLevel = new VBox();
        layoutLevel.setAlignment(Pos.BOTTOM_LEFT);

        layoutLevel.setPrefWidth(GameInformations.WINDOWSIZEX);
        layoutLevel.setPrefHeight(GameInformations.WINDOWSIZEY);

        Label levelLabel = new Label("Level: "+GameInformations.difficult+"/ Wave: "+wave);
        levelLabel.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 20px;");

        layoutLevel.getChildren().add(levelLabel);
        this.getChildren().add(layoutLevel);

        //playership life
        Label playerLife = new Label();
        this.getChildren().add(playerLife);
        playerLife.setTranslateX(20);
        playerLife.setTranslateY(20);
        playerLife.setStyle("-fx-font-family: 'Horizon'; -fx-font-size: 20px;");

        loop = new GameLoop() {


            @Override
            public void doFrame() {
                checkEnemies();
                //System.out.println("Enemies"+ enemies.size());
                levelSystem();
                highscoreLabel.setText("Highscore: "+GameInformations.highscore);
                levelLabel.setText("Level: "+GameInformations.difficult+"/ Wave: "+wave);
                playerLife.setText("Lifes: "+firstPlayer.getLife());
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
            if(wave==0){
                enemies.add(new TestGegner(this));
                enemies.get(1).setxKoord(GameInformations.WINDOWSIZEX/2);
                enemies.get(1).setyKoord(40);
            }
            else{
                Window.setActive("FinalWindow"); // das "Endfenster" einblenden
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
