package view;

import model.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MapWindow extends GameWindow {

    float[] backgroundRBGA = new float[]{0.4f, 0.7f, 0.3f, 0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();
    private Model model;

    public MapWindow(Model model) {
        this.model = model;
        buttons.add(new Button(Button.Id.ENTER, "./assets/EnterButton.png", 0, 0, 150, 50));
        buttons.add(new Button(Button.Id.RETURN, "./assets/ReturnButton.png", 650, 0, 150, 50));
        viewItems.addAll(buttons);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);
        for (GUIObject v : viewItems) {
            paint(v.getImagePath(),v.getX(),v.getY());
        }
        for(Entity e : model.entities){
            paint(assets.getImagepath(e.id),e.getX(),e.getY());
        }
    }

    protected void click(double posX, double posY) {
        int winHeight = getWindowHeight();
        posY = winHeight - posY;
        System.out.println("LClick (" + posX + ", " + posY + ")");

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).check(posX, posY)) {
                switch (buttons.get(i).id) {
                    case ENTER:
                        System.out.println("Moving to level");
                        notifyObservers(new ActionEvent(this, 0, "EnterLevel"));
                        break;
                    case RETURN:
                        System.out.println("Moving to main menu");
                        notifyObservers(new ActionEvent(this, 0, "MainMenu"));
                        break;
                }
            }
        }
    }
    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_LEFT:
                notifyObservers(new ActionEvent(this,0,"PointerLeft"));
                break;
            case GLFW_KEY_RIGHT:
                notifyObservers(new ActionEvent(this,0,"PointerRight"));
                break;
            case GLFW_KEY_ENTER:
                notifyObservers(new ActionEvent(this, 0, "EnterLevel"));
                break;
            case GLFW_KEY_ESCAPE:
                notifyObservers(new ActionEvent(this, 0, "MainMenu"));
                break;
        }
    }

}
