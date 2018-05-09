package model;

public class Player extends Entity{

    private int hp;
    private Weapon weapon;
    private int dx,dy;
    private int friction;
    private int gravity = 1;

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

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }


    public void update(){
        if(posY>=75){
            dy = dy - gravity;
        } else {
            dy = 0;
        }
        if(dx > 0) {
            dx = dx - friction;
        } else if(dx < 0){
            dx = dx + friction;
        }

        int next = posY+dy;
        if(next<75){
            posY = 75;
            posX = posX + dx;
        } else {
            posY = posY+dy;
            posX = posX + dx;
        }
        if(dx == 0){
            friction = 0;
        }
    }

    public void setFriction(int friction) {
        this.friction = friction;
    }

}
