package model;

public class Projectile extends Entity{

    private boolean isHostile;
    private int velocity;

    public Projectile(Id id,int posX, int posY, int width, int height, boolean isHostile, int velocity) {
        super(id,posX, posY, width, height);
        this.isHostile = isHostile;
        this.velocity = velocity;
    }

    public boolean getHostility(){
        return isHostile;
    }

    public int getVelocity(){
        return velocity;
    }
}
