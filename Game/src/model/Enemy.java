package model;

public class Enemy extends Entity{

    private int hp;


    public Enemy(int posX, int posY, int width, int height, int hp) {
        super(posX, posY, width, height);
        this.hp = hp;
    }

    public boolean overlaps(Projectile p){
        return super.overlaps(p) && !p.getHostility();
    }

    public boolean updateHP(int difference){
        this.hp = this.hp + difference;

        if(hp<=0)
            return true;

        return false;
    }
}
