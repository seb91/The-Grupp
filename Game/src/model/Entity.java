package model;

public abstract class Entity {

    protected int posX;
    protected int posY;

    protected int width;
    protected int height;

    public Id id;
    public enum Id {
        PLAYER, ENEMY, GROUND, PLATFORM, BALL, BOSSBALL
    }


    public Entity(Id id, int posX, int posY, int width, int height){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    //Checks if an entity has a corner that would exist within the given entity.
    public boolean overlaps(int x, int y,Entity e){

        /*int left = this.posX;
        int right = this.posX+this.width;
        int bottom = this.posY;
        int top = this.posY+height;*/
        /*
        return  ((this.posX > e.getX() && this.posX < e.getX()+e.getWidth()) && (this.posY+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                ((this.posX > e.getX() && this.posX < e.getX()+e.getWidth()) && (this.posY < e.getY()+e.getWidth()) && (this.posY > e.getY())) ||
                ((this.posX+this.width < e.getX()+e.getWidth() && this.posX+this.width > e.getX()) && (this.posY+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                ((this.posX+this.width < e.getX()+e.getWidth() && this.posX+this.width > e.getX()) && (this.posY < e.getY()+e.getWidth()) && (this.posY > e.getY()));
                */
        boolean upperY = y + height > e.getY() && y + height < e.getY()+e.getHeight();
        boolean lowerY = y < e.getY()+e.getHeight() && y > e.getY();
        boolean leftX = x > e.getX() && x < e.getX()+e.getWidth();
        boolean rightX = x + width < e.getX()+e.getWidth() && x + width > e.getX();

        return  (leftX && upperY) ||
                (leftX && lowerY) ||
                (rightX && upperY) ||
                (rightX && lowerY );
    }

    public Id getId(){
        return id;
    }

    public int getX(){
        return posX;
    }

    public int getY(){
        return posY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
