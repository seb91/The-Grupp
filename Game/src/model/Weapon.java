package model;

public class Weapon {
    private int dx;
    private int projectileWidth;
    private int getProjectileHeight;

    public Weapon() {
        this.dx = 8;
        this.projectileWidth = 20;
        this.getProjectileHeight = 10;
    }

    public Weapon(int dx, int projectileWidth, int getProjectileHeight) {
        this.dx = dx;
        this.projectileWidth = projectileWidth;
        this.getProjectileHeight = getProjectileHeight;
    }

    public Projectile fire(int x, int y, Player.Direction direction, int playerDx){

        int previous = Math.abs(dx);

        if(direction == Player.Direction.RIGHT){
            dx = previous;
            //player widht plus projectile width and dx to compensate for movement
            x = x+45+playerDx;
        } else {
            //player x minus projectile width
            x = x-25+playerDx;
            dx = previous*-1;
        }

        Projectile projectile = new Projectile(
                Entity.Id.PROJECTILE,
                x,
                y,
                projectileWidth,
                getProjectileHeight,
                dx
        );


        return projectile;
    }

}
