package view;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * The starting menu is generated here. It contains three button objects and an image object.
 *
 * Play button or Enter key: Notify controller, transition to the Save Menu
 * Options button: Notify controller, transition to the Options menu.
 * Quit button or Escape key: Notify controller, terminate program.
 *
 * @author Sebastian
 */
public class MainWindow extends GameWindow {

    private float[] backgroundRBGA = new float[]{1.0f,0.0f,0.0f,0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();


    public MainWindow(){
        buttons.add(new Button(Button.Id.PLAY, 325, 425,150,50));
        buttons.add(new Button(Button.Id.OPTIONS, 325, 350,150,50));
        buttons.add(new Button(Button.Id.QUIT, 325, 275,150,50));
        viewItems.addAll(buttons);
        viewItems.add(new Image("./assets/GameTitle.png", 150, 500));
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
                    case PLAY:
                        notifyObservers(new ActionEvent(this, 0, "SaveMenu"));
                        break;
                    case OPTIONS:
                        notifyObservers(new ActionEvent(this, 0, "OptionsMenu"));
                        break;
                    case QUIT:
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
