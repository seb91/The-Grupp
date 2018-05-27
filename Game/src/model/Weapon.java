package model;

public class Weapon {
    private int projectileDx;
    private int projectileWidth;
    private int projectileHeight;

    public Weapon() {
        this.projectileDx = 8;
        this.projectileWidth = 20;
        this.projectileHeight = 10;
    }

    public Weapon(int projectileDx, int projectileWidth, int projectileHeight) {
        this.projectileDx = projectileDx;
        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
    }

    public Projectile fire(int x, int y, int lastShooterDx){

        int directionDx = projectileDx;

        if(lastShooterDx >= 0){
            //spawn projectile fixed distance from shooter
            //some distance plus projectile width and dx to compensate for movement
            x = x+45+lastShooterDx;
        } else {
            //shooter x minus projectile width
            x = x-25+lastShooterDx;
            //projectile supposed to go to the left, hence we make dx negative
            directionDx *= -1;
        }

        Projectile projectile = new Projectile(
                Entity.Id.PROJECTILE,
                x,
                y,
                projectileWidth,
                projectileHeight,
                directionDx
        );

        return projectile;
    }

}
