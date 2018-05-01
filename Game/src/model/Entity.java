package model;

public class Entity {

    protected int x,y;
    public Id id;
    public enum Id {
        NODE, LOCKED_NODE, BOSS_NODE, LOCKED_BOSS_NODE,
        CHARACTER, ENEMY, TERRAIN, POINTER
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
