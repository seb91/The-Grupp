package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.opengl.GL11.*;

/*
 * Window that is shown when game is paused while in the LevelWindow. Allows the user to
 * exit the level or return to main menu.
 *
 * @author Alexander
 * @author Eric
 */
public class PausedWindow extends GameWindow {

    private float[] backgroundRBGA;

    private List<Button> buttons = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();
    private int saveSlot;
    private LevelWindow lvl;

    public PausedWindow (int saveSlot, LevelWindow lvl) {
        this.lvl=lvl;
        this.saveSlot = saveSlot;
        buttons.add(new Button(Button.Id.RESUME, 300, 425, 150, 50));
        buttons.add(new Button(Button.Id.MAP, 300, 350, 150, 50));
        buttons.add(new Button(Button.Id.MENU, 300, 275,150,50));
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

        for(Button b: buttons){
            if (b.check(posX, posY)) {
                switch (b.id) {
                    case RESUME:
                        notifyListeners(new ActionEvent(lvl, saveSlot, "Resume"));
                        break;
                    case MAP:
                        notifyListeners(new ActionEvent(this, saveSlot, "Save"));
                        break;
                    case MENU:
                        notifyListeners(new ActionEvent(this, 0, "MainMenu"));
                        break;
                }
            }
        }
    }

    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_ENTER:
                notifyListeners(new ActionEvent(this, saveSlot, "Save"));
                break;
            case GLFW_KEY_ESCAPE:
                notifyListeners(new ActionEvent(lvl, saveSlot, "Resume"));
                break;
        }
    }
}
