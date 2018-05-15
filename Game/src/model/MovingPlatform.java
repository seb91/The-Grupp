package model;

import services.CheckCollision;

public class MovingPlatform extends MovingEntity {
    private int originX;
    private int originY;
    private int speed;
    private int path;

    public MovingPlatform(Id id, int posX, int posY, int width, int height) {
        super(id, posX, posY, width, height);
        speed = 1;
        path = 40;
        originX=posX;
        originY=posY;
    }

    @Override
    public void collision(Entity b) {
        CheckCollision.collision(this, b);
    }

    public void update(){
        if (posX - originX == path || posX - originX == -path){
            speed = -speed;
        }
        posX+= speed;
    }
}
