package view;

import model.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelWindow extends GameWindow {


    int cameraX = 0;
    int cameraY = 0;
    int cameraWidth = 800;
    int cameraHeight = 600;

    float[] backgroundRBGA = new float[]{0.70f, 0.88f, 0.99f, 0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();
    private Level model;
    private HealthBar hp;

    public LevelWindow(Level model) {
        this.model = model;
        buttons.add(new Button(Button.Id.RETURN, 650, 0, 150, 50));
        hp =new HealthBar(HealthBar.Id.HEALTH_BAR,10,560);
        viewItems.add(hp);
        viewItems.addAll(buttons);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);
        for (Entity e : model.getEntities()) {
            if(overlapsCamera(e)){
                paint(GameWindow.assets.getEPath(e.getId()), e.getX()-cameraX, e.getY());
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
        if(model.getPlayerHealth()>=1) {
            hp.updateHealthBar(model.getPlayerHealth());
        } else{
            notifyObservers(new ActionEvent(this, saveData, "Save"));
        }
        model.update();
    }

    public boolean overlapsCamera(Entity e){
        return cameraX <= e.getX();
    }

    @Override
    protected void click(double posX, double posY) {
        int winHeight = getWindowHeight();
        posY = winHeight - posY;
        System.out.println("LClick (" + posX + ", " + posY + ")");

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).check(posX, posY)) {
                switch (buttons.get(i).id) {
                    case RETURN:
                        System.out.println("Moving to map");
                        notifyObservers(new ActionEvent(this, saveData , "Save"));
                        break;
                }
            }
        }
    }

    @Override
    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_LEFT:
                model.moveLeft();
                break;
            case GLFW_KEY_RIGHT:
                System.out.println("Right button pressed, update character model");
                model.moveRight();
                break;
            case GLFW_KEY_SPACE:
                System.out.println("Space key pressed, update model");
                model.jump();
                break;
            case GLFW_KEY_ESCAPE:
                System.out.println("Escape key pressed, moving to map");
                notifyObservers(new ActionEvent(this, saveData, "Save"));
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
                path = path+3+".png";
            }else if(step.equals("3")){
                path = path +4+".png";
            }  else {
                path = path +3+".png";
            }
            assets.updateLevelAsset(e.id,path);
        }

        if(lastX+20 <= e.getX()){
            e.setLastPosX(e.getX());
            if(step.equals("3")||step.equals("4")){
                path = path+1+".png";
            }else if(step.equals("1")){
                path = path +2+".png";
            }  else  {
                path = path +1+".png";
            }
            assets.updateLevelAsset(e.id,path);
        }

    }
    protected void released(int key) {
        switch (key) {
            case GLFW_KEY_LEFT:
                System.out.println("Left key released");
                model.stopMoving(key);
                break;
            case GLFW_KEY_RIGHT:
                System.out.println("Right key released");
                model.stopMoving(key);
                break;
        }
    }
}