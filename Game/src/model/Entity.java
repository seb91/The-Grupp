package model;

public abstract class Entity {

    protected int posX;
    protected int posY;

    private int width;
    private int height;

    public Id id;
    public enum Id {
        PLAYER, ENEMY, GROUND, PLATFORM,
    }


    public Entity(Id id, int posX, int posY, int width, int height){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public boolean overlaps(Entity e){

        /*int left = this.posX;
        int right = this.posX+this.width;
        int bottom = this.posY;
        int top = this.posY+height;*/

        return  ((this.posX > e.getX() && this.posX < e.getX()+e.getWidth()) && (this.posY+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                ((this.posX > e.getX() && this.posX < e.getX()+e.getWidth()) && (this.posY < e.getY()+e.getWidth()) && (this.posY > e.getY())) ||
                ((this.posX+this.width < e.getX()+e.getWidth() && this.posX+this.width > e.getX()) && (this.posY+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                ((this.posX+this.width < e.getX()+e.getWidth() && this.posX+this.width > e.getX()) && (this.posY < e.getY()+e.getWidth()) && (this.posY > e.getY()));
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
