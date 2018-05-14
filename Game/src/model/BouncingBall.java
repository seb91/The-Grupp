package model;

public class BouncingBall extends Entity {
    private int dx,dy;
    private int friction;
    private int gravity = 1;
    private int fallLimit,leftLimit, rightLimit;

    public BouncingBall(Id id, int posX, int posY, int width, int height) {
        super(id, posX, posY, width, height);
    }
    public int nextX(){
        return posX+dx;
    }
    public int nextY(){
        return posY+dy;
    }

    public void update(){

        if(dx > 0) {
            dx = dx - friction;
        } else if(dx < 0){
            dx = dx + friction;
        }

        if(nextY()< fallLimit){
            posY = fallLimit;

        } else {
            posY = posY+dy;
        }

        if(nextX() < leftLimit ){
            posX = leftLimit;
        } else if(nextX() > rightLimit ) {
            posX = rightLimit;
        } else {
            posX = posX + dx;
        }

        if(dx == 0){
            friction = 0;
        }
        if(posY > fallLimit) {
            dy = dy - gravity;
        } else {
            dy = 0;
        }
    }
}
