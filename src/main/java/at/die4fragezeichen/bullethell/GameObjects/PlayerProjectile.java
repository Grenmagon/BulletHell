package at.die4fragezeichen.bullethell.GameObjects;

import at.die4fragezeichen.bullethell.GameInformations;

public class PlayerProjectile extends Projectile{
    public PlayerProjectile(GamePolygon spawner, double direction, double speed) {
        super(spawner, direction, speed);
        // Projektil zeichnen
        getPoints().addAll(
                10.0,10.0, // Koordinate 1 Ecke
                10.0,2.0,
                2.0,2.0,
                2.0,10.0,
                10.0,10.0
        );


        setDamage(1);
    }


    @Override
    protected void setHitEntity(GamePolygon hittedpolygon) {
        System.out.println("hab getroffen!!");
        //Abchecken was wurde getroffen und dementsprechend handeln
        if(hittedpolygon == getSpawner()){
            return;
        }else if(hittedpolygon instanceof Projectile){ // wenn ein anderes Projektil getroffen wird
            setRemovePolygon();
            return;
        } else if (hittedpolygon instanceof Entity) { // wenn der Gegner getroffen wird, wird Projektil entffernt, was sonst noch passiert entscheidet die Entity
            setRemovePolygon();
        }


    }
}
