package model;

import java.util.Random;

public class Enemy extends Player{

    int bounds;

    public Enemy(Id id,int posX, int posY, int width, int height, int hp) {
        super(id,posX, posY, width, height,hp);
        bounds =-1;
    }

    private void randomMovement(){
        Random random = new Random();
        int n = random.nextInt(10) +1;
        if (n<5){
            dx = 1;
        }
        if (n>5){
            dx = -1;
        }
        if(n<4&&posY<=fallLimit){
            dy = 15;
        }
    }
    public void move(){
        if(bounds-100>=posX||bounds+100<=posX){
            bounds = posX;
            randomMovement();
        } else if(posX==rightLimit||posX==leftLimit){
            randomMovement();
        }
    }
}
