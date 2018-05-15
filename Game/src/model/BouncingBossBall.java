package model;

import services.CheckCollision;

public class BouncingBossBall extends MovingEntity {

    private int speedX;
    private int speedY;
    private int gravity = 1;
    public BouncingBossBall(Id id, int posX, int posY, int width, int height) {
        super(id, posX, posY, width, height);
        this.speedX = 10;
        this.speedY = 10;
    }

    @Override
    public void collision(Entity b) {
        CheckCollision.collision(this,b);
    }

    public void update() {
            // detect collision with the border
            if (posX < width || posX > 800 - width || nextX() < leftLimit) {
                speedX *= -1; // change direction of ball
            }
            if (posY < width) {
                speedY *= -1;
                posY=width;
            }
            if (posY > 600 - width) {
                speedY *= -1;
                posY= 600 - width;
            }

            // compute new position according to the speed of the ball
            posX +=  speedX;
            posY +=  speedY;
    }
}
