package model;

/*
 * An object with custom functionality that regains its y-axis velocity on collision.
 * This ball only travels on the y-axis.
 *
 * @author Alexander
 */
public class BouncingBall extends MovingEntity {
    private int dy;
    private int gravity = 1;

    public BouncingBall(Id id, int posX, int posY, int width, int height) {
        super(id, posX, posY, width, height);
    }
    public int nextY(){
        return posY+dy;
    }

    @Override
    public void collision(Entity b) {
        CheckCollision.collision(this, b);
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void update(){
        if (posY == fallLimit) {
            dy = 20;
        }
        if(nextY()< fallLimit){
            posY = fallLimit;
        } else {
            posY = posY+dy;
        }
        if(posY > fallLimit) {
            dy = dy - gravity;
        } else {
            dy = 0;
        }
    }
}

