package model;

public class Player extends MovingEntity{

    private Weapon weapon;
    private Long tookDamage = System.currentTimeMillis()-1000;
    private int hp;

    private Direction direction;
    public enum Direction {
        LEFT, RIGHT
    };

    public Player(Id id,int posX, int posY, int width, int height, int hp) {
        super(id,posX, posY, width, height);
        this.hp = hp;
        lastPosX = posX;
        this.weapon = new Weapon("The Gun", 8, 20, 10);
        direction = Direction.RIGHT;
    }

    public void setDirection(Direction d){
        this.direction = d;
    }

    public Direction getDirection(){
        return direction;
    }

    public Projectile fireProjectile(){
        return weapon.fire(this.getX(), this.getY()+this.getHeight()/6*2, direction, this.getDx());
    }

    /*
     Updates player's HP. Returns true if this kills the player, or false
     if player survives.
     */
    public boolean updateHP(int difference){
        this.hp = this.hp + difference;

        if(hp<=0)
            return true;

        return false;
    }
    public int getHealth() {
        return hp;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    public int nextX(){
        return posX+dx;
    }
    public int nextY(){
        return posY+dy;
    }

    public void damageCheck(Entity.Id id){
        Long currentTime = System.currentTimeMillis();
        if((this.id == Id.PLAYER&& (id==Id.ENEMY || id==Id.BALL || id == Id.BOSSBALL))&&(tookDamage - currentTime < -1000)){
            updateHP(-1);
            tookDamage = currentTime;
        }
    }

    //Checks collisions for objects.
    public void collision(Entity e){
        CheckCollision.collision(this,e);
    }

    public void update(){
        if(dx > 0) {
            dx = dx - friction;
        } else if(dx < 0){
            dx = dx + friction;
        }

        if(nextY()< fallLimit){
            posY = fallLimit;

        } else {
            posY = posY+dy;
        }

        if(nextX() < leftLimit || posX <leftLimit){
            posX = leftLimit;
        } else if(nextX() > rightLimit || posX >rightLimit) {
            posX = rightLimit;
        } else {
            posX = posX + dx;
        }

        if(dx == 0){
            friction = 0;
        }
        if(posY > fallLimit) {
            dy = dy - gravity;
        } else {
            dy = 0;
        }
    }

    public void setFriction(int friction) {
        this.friction = friction;
    }


    public int getFallLimit() {
        return fallLimit;
    }
}
