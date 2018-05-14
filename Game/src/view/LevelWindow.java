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

    public LevelWindow(Level model) {
        this.model = model;
        buttons.add(new Button(Button.Id.RETURN, 650, 0, 150, 50));
        viewItems.addAll(buttons);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);

        for (Entity e : model.getEntities()) {
            if(overlapsCamera(e)){
                paint(GameWindow.assets.getEPath(e.getId()), e.getX()-cameraX, e.getY());
            }
        }

        if(model.getPlayerX()-cameraX > 550){
            cameraX = model.getPlayerX()-550;
        }

        for (GUIObject v : viewItems) {
            paint(v.getImagePath(),v.getX(),v.getY());

        }


        model.update();
    }

    public boolean overlapsCamera(Entity e){

        return cameraX <= e.getX() && cameraX+cameraWidth >= e.getX()+e.getWidth() ;

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
                        System.out.println("Moving to main menu");
                        notifyObservers(new ActionEvent(this, 0, "Map"));
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
                notifyObservers(new ActionEvent(this, 0, "Map"));
                break;
        }
    }
    protected void released(int key) {
        switch (key) {
            case GLFW_KEY_LEFT:
                System.out.println("Left key released");
                model.stopMoving();
                break;
            case GLFW_KEY_RIGHT:
                System.out.println("Right key released");
                model.stopMoving();
                break;
        }
    }
}