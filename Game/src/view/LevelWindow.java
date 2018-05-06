package view;

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

    //temp
    private int model;

    public LevelWindow(int model) {
        this.model = model;
        viewItems.add(new Image("./assets/GroundTexture.png", 0, 0));
        buttons.add(new Button(Button.Id.RETURN, 650, 0, 150, 50));
        viewItems.addAll(buttons);

    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);
        for (GUIObject v : viewItems) {

            if(overlapsCamera(v)){
                paint(v.getImagePath(),cameraX-v.getX(),cameraY-v.getY());
                paint("./assets/CharacterTexture.png", model,75);
            }



        }
    }

    public boolean overlapsCamera(GUIObject e){

        /*int left = this.posX;
        int right = this.posX+this.width;
        int bottom = this.posY;
        int top = this.posY+height;*/

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
                //notifyObservers(new ActionEvent(this,0,"CharacterLeft"));
                cameraX = cameraX - 1;
                break;
            case GLFW_KEY_RIGHT:
                System.out.println("Right button pressed, update character model");
                //notifyObservers(new ActionEvent(this,0,"CharacterRight"));
                cameraX = cameraX + 1;
                break;
            case GLFW_KEY_ESCAPE:
                System.out.println("Escape key pressed, moving to map");
                notifyObservers(new ActionEvent(this, 0, "Map"));
                break;
        }
    }
}