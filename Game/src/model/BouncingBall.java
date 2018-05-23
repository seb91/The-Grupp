package model;

public class BouncingBall extends MovingEntity {
    private int dx,dy;
    private int friction;
    private int gravity = 1;
    private int fallLimit,leftLimit, rightLimit;

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

