package model;

import java.util.Random;

public class Enemy extends Entity{

    private int hp;



    public Enemy(Id id,int posX, int posY, int width, int height, int hp) {
        super(id,posX, posY, width, height);
        this.hp = hp;
    }

   /* public boolean overlaps(Projectile p){
        return super.overlaps(p) && !p.getHostility();
    }*/

    public boolean updateHP(int difference){
        this.hp = this.hp + difference;

        if(hp<=0)
            return true;

        return false;
    }

    public void randomMovement(){
        Random random = new Random();
        int n = random.nextInt(10) +1;
        if (n<5){
            posX+= n;
        }
        if (n>5){
            posX-= n-5;
        }
    }
}
