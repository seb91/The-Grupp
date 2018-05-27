package view;

import model.*;
import services.SaveGame;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * This is the core view of the game.
 *
 * The class is created based on the Model and all containing Entities.
 * It has a single button and a health bar GUI object and renders these, as well as the model entities.
 * This class continually re-renders based on the ever changing model.
 *
 * @author Sebastian
 * @author Eric
 */
public class LevelWindow extends GameWindow {

    private Camera camera;

    private float[] backgroundRBGA = new float[]{0.70f, 0.88f, 0.99f, 0.0f};

    private List<GUIObject> viewItems = new ArrayList<>();
    private Level model;
    private HealthBar hp;
    private boolean isPaused;

    public LevelWindow(Level model) {
        this.model = model;
        hp =new HealthBar(HealthBar.Id.HEALTH_BAR,10,560);
        viewItems.add(hp);
        camera = new Camera(0, 0, getWindowWidth(), getWindowHeight());
    }

    private void update(){

        if(model.levelComplete){
            if(saveData<model.getLevelNr()+1) {
                SaveGame.saveGame(model.getLevelNr() + 1, saveSlot);
            }
            notifyListeners(new ActionEvent(this, saveSlot, "Finish"));
        }

        int breakpoint = (int) (camera.getWidth()/1.5);

        if(model.getPlayerX()-camera.getX() > breakpoint){
            camera.setX(model.getPlayerX()-breakpoint);
            CheckCollision.setLeft(camera.getX());
        }

        if(model.getPlayerY()<=0 || model.getPlayerHealth()<=0){
            notifyListeners(new ActionEvent(this, saveSlot, "Dead"));
        } else {
            hp.updateHealthBar(model.getPlayerHealth());
        }

        if (!isPaused) {
            model.update();
        }

    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);

        update();

        ArrayList<Entity> copy = new ArrayList<>(model.getEntities());

        for (Entity e : copy) {
            if(camera.overlaps(e)){
                paint(GameWindow.assets.getEPath(e.getId()), e.getX()-camera.getX(), e.getY());
            } else {
                if(e.getId() == Entity.Id.PROJECTILE){
                    model.getEntities().remove(e);
                }
            }

            if(e.id == Entity.Id.PLAYER){
                animate(e);
            }
            if(e.id == Entity.Id.ENEMY){
                animate(e);
            }
        }

        for (GUIObject v : viewItems) {
            paint(v.getImagePath(),v.getX(),v.getY());
        }
    }

    protected void click(double posX, double posY) {
    }

    @Override
    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_LEFT:
                model.moveLeft();
                break;
            case GLFW_KEY_RIGHT:
                model.moveRight();
                break;
            case GLFW_KEY_SPACE:
                model.jump();
                break;
            case GLFW_KEY_ESCAPE:
                isPaused=!isPaused;
                notifyListeners(new ActionEvent(this, saveSlot, "Pause"));
                break;
            case GLFW_KEY_Z:
                model.playerAttack();
                break;
        }
    }
    private void animate (Entity e){
        int lastX = e.getLastPosX();
        String path = assets.getEPath(e.id);
        String step = path.substring(path.length()-5, path.length()-4);
        path = path.substring(0, path.length() - 5);

        if(lastX-20 >= e.getX()){
            e.setLastPosX(e.getX());
            if(step.equals("1")||step.equals("2")){
                path += 3+".png";
            }else if(step.equals("3")){
                path += 4+".png";
            }  else {
                path += 3+".png";
            }
            assets.updateLevelAsset(e.id,path);
        }

        if(lastX+20 <= e.getX()){
            e.setLastPosX(e.getX());
            if(step.equals("3")||step.equals("4")){
                path += 1+".png";
            }else if(step.equals("1")){
                path += +2+".png";
            }  else  {
                path += +1+".png";
            }
            assets.updateLevelAsset(e.id,path);
        }

    }
    protected void released(int key) {
        switch (key) {
            case GLFW_KEY_LEFT:
                model.stopMoving(key);
                break;
            case GLFW_KEY_RIGHT:
                model.stopMoving(key);
                break;
        }
    }
    public void resume(boolean bool){
        released(GLFW_KEY_LEFT);
        released(GLFW_KEY_RIGHT);
        isPaused=bool;
    }
}