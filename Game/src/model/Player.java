package model;

public class Player extends Entity{

    private int hp;
    private Weapon weapon;
    private int dx,dy;
    private int friction;
    private Entity standingOn;
    private int gravity = 1;
    private int fallLimit,leftLimit, rightLimit;

    public Player(Id id,int posX, int posY, int width, int height, int hp) {
        super(id,posX, posY, width, height);
        this.hp = hp;
    }

    /*public boolean overlaps(Projectile p){
        return super.overlaps(p) && p.getHostility();
    }*/


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
    public int nextX(){
        return posX+dx;
    }
    public int nextY(){
        return posY+dy;
    }

    //Checks collisions for objects.
    public void collision(Entity e){

        if(e!=this) {
            boolean rightOfEntity = posX >= e.getX()+e.getWidth();
            boolean leftOfEntity = posX+width <= e.getX();

            boolean belowEntity = posY+height <= e.getY();
            boolean aboveEntity = posY >= e.getY() + e.getHeight();
            boolean withinEntityWidth = posX+width > e.getX() && posX < e.getX() + e.getWidth();

            //Checks if there would be an overlap in the next render
            if (overlaps(nextX(),nextY(),e)||e.overlaps(e.getX()-dx,e.getY()+dy,this)) {
                System.out.println("Player collided with " + e.id);

                //From above
                if (aboveEntity && withinEntityWidth) {
                    fallLimit = e.getY() + e.getHeight();
                    standingOn = e;
                    System.out.println("Fall limit is now " + (e.getY() + e.getHeight()));
                }
                //From below
                else if(belowEntity && withinEntityWidth) {
                    setDy(0);
                    posY = e.getY()-height-1;
                }
                //From the Right
                else if(rightOfEntity){
                    leftLimit = e.getX() + e.getWidth();
                }
                //From the Left
                else if(leftOfEntity){
                    rightLimit = e.getX()-width;
                }

            } else if(!withinEntityWidth && standingOn == e){
                fallLimit = 0;
            }
        } else {
            leftLimit = 0;
            // to be set to level width later
            rightLimit = 1000;
        }

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

        if(nextX() < leftLimit ){
            posX = leftLimit;
        } else if(nextX() > rightLimit ) {
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
