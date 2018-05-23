package model;

public class Projectile extends MovingEntity{

    private boolean isHostile;

    public Projectile(Id id,int posX, int posY, int width, int height, boolean isHostile, int dx) {
        super(id,posX, posY, width, height);
        this.isHostile = isHostile;
        this.setDx(dx);
    }

    public boolean getHostility(){
        return isHostile;
    }

    @Override
    public void collision(Entity e) {
        CheckCollision.collision(this, e);
    }

    public void updatePosition(){
        this.posX = posX + dx;
    }
}
