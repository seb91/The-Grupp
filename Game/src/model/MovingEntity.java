package model;

public abstract class MovingEntity extends Entity {
    protected int dx,dy;
    protected int friction;
    protected Entity standingOn;
    protected int gravity = 1;
    protected int fallLimit,leftLimit, rightLimit;
    public MovingEntity(Id id, int posX, int posY, int width, int height) {
        super(id, posX, posY, width, height);
    }

    public int nextX(){
        return posX+dx;
    }
    public int nextY(){
        return posY+dy;
    }

    public abstract void collision(Entity b);
    public int getRightLimit() {
        return rightLimit;
    }

    public void setRightLimit(int rightLimit) {
        this.rightLimit = rightLimit;
    }

    public int getLeftLimit() {
        return leftLimit;
    }

    public void setLeftLimit(int leftLimit) {
        this.leftLimit = leftLimit;
    }

    public int getFallLimit() {
        return fallLimit;
    }

    public void setFallLimit(int fallLimit) {
        this.fallLimit = fallLimit;
    }

    public Entity getStandingOn() {
        return standingOn;
    }

    public void setStandingOn(Entity standingOn) {
        this.standingOn = standingOn;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
}
