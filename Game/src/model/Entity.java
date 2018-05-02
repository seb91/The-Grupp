package model;

public class Entity {

    private int posX;
    private int posY;

    private int width;
    private int height;

    public Entity(int posX, int posY, int width, int height){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public boolean overlaps(Entity e){

        int left = this.posX;
        int right = this.posX+this.width;
        int bottom = this.posY;
        int top = this.posY+height;

        return  ((this.posX > e.getX() && this.posX < e.getX()+e.getWidth()) && (this.posY+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                ((this.posX > e.getX() && this.posX < e.getX()+e.getWidth()) && (this.posY < e.getY()+e.getWidth()) && (this.posY > e.getY())) ||
                ((this.posX+this.width < e.getX()+e.getWidth() && this.posX+this.width > e.getX()) && (this.posY+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                ((this.posX+this.width < e.getX()+e.getWidth() && this.posX+this.width > e.getX()) && (this.posY < e.getY()+e.getWidth()) && (this.posY > e.getY()));
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
