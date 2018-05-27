package view;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * Simple window that allows the user to modify program state. At the moment, only muting
 * the music is supported, but additional functionality can be added should it be needed.
 *
 * @author Eric
 */
public class OptionsWindow extends GameWindow {

    private float[] backgroundRBGA = new float[]{0.0f,0.0f,1.0f,0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();

    public OptionsWindow(){
        buttons.add(new Button(Button.Id.RETURN, 325, 350,150,50));
        buttons.add(new Button(Button.Id.BG_MUSIC_TOGGLE, 325, 425,150,50));
        viewItems.addAll(buttons);
        viewItems.add(new Image("./assets/OptionsTitle.png", 150, 500));
    }

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

        for(Button b: buttons){
            if (b.check(posX, posY)) {
                switch (b.id) {
                    case RETURN:
                        notifyListeners(new ActionEvent(this, 2, "MainMenu"));
                        break;
                    case BG_MUSIC_TOGGLE:
                        if (musicOn) {
                            musicOn = false;
                            terminateAudio(Audio.Id.BG_MUSIC);
                        } else{
                            musicOn = true;
                            playAudio(Audio.Id.BG_MUSIC);
                        }
                        break;
                }
            }
        }
    }
    @Override
    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_ESCAPE:
                notifyListeners(new ActionEvent(this, 2, "MainMenu"));
                break;
        }
    }
}
