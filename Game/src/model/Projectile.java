package model;

/*
 * Used by the Weapon class, is simply a directional object continually updating
 * coordinates until collision.
 *
 * @author Eric
 */
public class Projectile extends MovingEntity{

    public Projectile(Id id,int posX, int posY, int width, int height, int dx) {
        super(id,posX, posY, width, height);
        this.setDx(dx);
    }

    @Override
    public void collision(Entity e) {
        CheckCollision.collision(this, e);
    }

    public void updatePosition(){
        this.posX = posX + dx;
    }
}
