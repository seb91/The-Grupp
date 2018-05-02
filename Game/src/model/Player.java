package model;

public class Player extends Entity{

    private int hp;
    private Weapon weapon;

    public Player(Id id,int posX, int posY, int width, int height, int hp) {
        super(id,posX, posY, width, height);
        this.hp = hp;
    }

    public boolean overlaps(Projectile p){
        return super.overlaps(p) && p.getHostility();
    }


    public boolean updateHP(int difference){
        this.hp = this.hp + difference;

        if(hp<=0)
            return true;

        return false;
    }
}
