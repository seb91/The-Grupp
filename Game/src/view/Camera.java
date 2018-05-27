package view;

import model.Entity;

/*
 * The camera is the core of the side-scrolling mechanic.
 *
 * Its used when LevelWindow paints entities from model, based on how far the character has
 * moved the cameras sets what entities may get painted and how far their coordinated should
 * be modified when painted.
 *
 * @author Eric
 */
public class Camera {

    private int cameraX;
    private int cameraY;
    private int cameraWidth;
    private int cameraHeight;

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
