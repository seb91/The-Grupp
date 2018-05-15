/*
* A simple view containing three options to load a game.
* @Author Alexander
* */
package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.opengl.GL11.*;

public class SaveWindow extends GameWindow{
    float[] backgroundRBGA = new float[]{1.0f,0.0f,0.0f,0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();


    public SaveWindow(){
        buttons.add(new Button(Button.Id.SAVE1, 325, 425,150,50));
        buttons.add(new Button(Button.Id.SAVE2,325, 350,150,50));
        buttons.add(new Button(Button.Id.SAVE3, 325, 275,150,50));
        buttons.add(new Button(Button.Id.RETURN, 325, 200,150,50));
        viewItems.addAll(buttons);
        viewItems.add(new Image("./assets/GameTitle.png", 150, 500));
    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);

        for (GUIObject v : viewItems) {
            paint(v.getImagePath(),v.getX(),v.getY());
        }
    }

    @Override
    protected void click(double posX, double posY) {
        int winHeight = getWindowHeight();
        posY = winHeight - posY;
        System.out.println("MClick (" + posX + ", " + posY + ")");

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).check(posX, posY)) {
                switch (buttons.get(i).id) {
                    case SAVE1:
                        System.out.println("Recreating game from save slot 1");
                        notifyObservers(new ActionEvent(this, 0, "Save1"));
                        break;
                    case SAVE2:
                        System.out.println("Recreating game from save slot 2");
                        notifyObservers(new ActionEvent(this, 0, "Save2"));
                        break;
                    case SAVE3:
                        System.out.println("Recreating game from save slot 3");
                        notifyObservers(new ActionEvent(this, 0, "Save3"));
                        break;
                    case RETURN:
                        System.out.println("Returning to main menu.");
                        notifyObservers(new ActionEvent(this, 0, "MainMenu"));
                        break;
                }
            }
        }
    }
    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_ENTER:
                System.out.println("Recreating game from save slot 1");
                notifyObservers(new ActionEvent(this, 0, "Save1"));
                break;
        }
    }
}
