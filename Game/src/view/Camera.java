package view;

import model.Entity;

public class Camera {

    int cameraX;
    int cameraY;
    int cameraWidth;
    int cameraHeight;

    public Camera(int cameraX, int cameraY, int cameraWidth, int cameraHeight){
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;
    }

    public boolean overlaps(Entity e){

        return (cameraX <= e.getX() || cameraX <= e.getX()+e.getWidth()) &&
                (cameraX+cameraWidth >= e.getX()+e.getWidth() || cameraX+cameraWidth >= e.getX());

    }

    public int getX(){
        return cameraX;
    }

    public int getY(){
        return cameraY;
    }

    public void setX(int x){
        cameraX = x;
    }

    public void setY(int y){
        cameraY = y;
    }

    public int getWidth(){
        return cameraWidth;
    }

    public int getHeight(){
        return cameraHeight;
    }

}
