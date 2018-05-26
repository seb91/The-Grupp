package view;

import model.*;
import services.SaveGame;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelWindow extends GameWindow {

    private Camera camera;

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
        camera = new Camera(0, 0, getWindowWidth(), getWindowHeight());
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);

        if(model.levelComplete){
            if(saveData<model.getLevelNr()+1) {
                System.out.println("Level nr: " + model.getLevelNr() + ", " + "Save slot: " + saveSlot);
                SaveGame.saveGame(model.getLevelNr() + 1, saveSlot);
            }
            notifyObservers(new ActionEvent(this, saveSlot, "Finish"));
        }

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
        int breakpoint = (int) (camera.getWidth()/1.5);

        if(model.getPlayerX()-camera.getX() > breakpoint){
            camera.setX(model.getPlayerX()-breakpoint);
            CheckCollision.setLeft(camera.getX());
        }

        for (GUIObject v : viewItems) {
            paint(v.getImagePath(),v.getX(),v.getY());
        }

        if(model.getPlayerY()<=0 || model.getPlayerHealth()<=0){
            notifyObservers(new ActionEvent(this, saveSlot, "Dead"));
            //notifyObservers(new ActionEvent(this, saveData, "Save"));
        } else {
            hp.updateHealthBar(model.getPlayerHealth());
        }
        model.update();
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
                        notifyObservers(new ActionEvent(this, saveSlot, "Save"));
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
                //cameraX -= 1;
                break;
            case GLFW_KEY_RIGHT:
                System.out.println("Right button pressed, update character model");
                model.moveRight();
                //cameraX += 1;
                break;
            case GLFW_KEY_SPACE:
                System.out.println("Space key pressed, update model");
                model.jump();
                break;
            case GLFW_KEY_ESCAPE:
                System.out.println("Escape key pressed, moving to map");
                notifyObservers(new ActionEvent(this, saveSlot, "Save"));
                break;
            case GLFW_KEY_Z:
                System.out.println("Z key pressed, firing weapon");
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