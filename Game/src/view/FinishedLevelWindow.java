/*
*A view to be shown when the level is ended, either by dying or by reaching the goal. Also works as a pause window.
 */
package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.opengl.GL11.*;

public class FinishedLevelWindow extends GameWindow{
    float[] backgroundRBGA = new float[]{1.0f,0.0f,0.0f,0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();
    private int saveSlot;
    private LevelWindow lvl;

    public FinishedLevelWindow(int saveSlot, boolean isGoal) {
        this.saveSlot = saveSlot;
        buttons.add(new Button(Button.Id.MAP, 300, 425, 150, 50));
        buttons.add(new Button(Button.Id.MENU, 300, 350,150,50));
        buttons.add(new Button(Button.Id.QUIT, 325, 275, 150, 50));
        viewItems.addAll(buttons);
        if (isGoal) {
            viewItems.add(new Image("./assets/GameFinished.png", 150, 500));
            backgroundRBGA = new float[]{0f,1.0f,0.0f,0.0f};
        } else {
            viewItems.add(new Image("./assets/GameOver.png", 150, 500));
            backgroundRBGA = new float[]{1.0f,0.0f,0.0f,0.0f};
        }
    }

    public FinishedLevelWindow(int saveSlot, boolean isGoal, LevelWindow lvl) {
        this.lvl=lvl;
        this.saveSlot = saveSlot;
        buttons.add(new Button(Button.Id.RESUME, 300, 425, 150, 50));
        buttons.add(new Button(Button.Id.MAP, 300, 350, 150, 50));
        buttons.add(new Button(Button.Id.MENU, 300, 275,150,50));
        buttons.add(new Button(Button.Id.QUIT, 325, 200, 150, 50));
        viewItems.addAll(buttons);
        viewItems.add(new Image("./assets/GamePaused.png", 150, 500));
        backgroundRBGA = new float[]{0f,1.0f,0.0f,0.0f};
    }

    public void render(){
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
                    case RESUME:
                        System.out.println("Resuming game");
                        notifyObservers(new ActionEvent(lvl, saveSlot, "Resume"));
                        break;
                    case MAP:
                        System.out.println("Moving to map with saved progress");
                        notifyObservers(new ActionEvent(this, saveSlot, "Save"));
                        break;
                    case MENU:
                        System.out.println("Moving to Main menu");
                        notifyObservers(new ActionEvent(this, 0, "MainMenu"));
                        break;
                    case QUIT:
                        System.out.println("Terminating program");
                        notifyObservers(new ActionEvent(this, 0, "Exit"));
                        break;
                }
            }
        }
    }
    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_ENTER:
                notifyObservers(new ActionEvent(this, 0, "SaveMenu"));
                break;
            case GLFW_KEY_ESCAPE:
                notifyObservers(new ActionEvent(this, 0, "Exit"));
                break;
        }
    }
}

