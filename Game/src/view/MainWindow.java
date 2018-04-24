package view;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;

import controller.Game;
import services.Texture;

public class MainWindow extends GameWindow {

    float[] backgroundRBGA = new float[]{1.0f,0.0f,0.0f,0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<ViewObject> viewItems = new ArrayList<>();


    public MainWindow(){
        buttons.add(new Button(Button.Id.PLAY,"./assets/PlayButton.png", 325, 425,150,50));
        buttons.add(new Button(Button.Id.OPTIONS,"./assets/OptionsButton.png", 325, 350,150,50));
        buttons.add(new Button(Button.Id.QUIT,"./assets/QuitButton.png", 325, 275,150,50));
        viewItems.addAll(buttons);
        viewItems.add(new Image("./assets/GameTitle.png", 150, 500 ,500,100));
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);

        for (ViewObject v : viewItems) {
            paint(v);
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
                    case PLAY:
                        System.out.println("Moving to Save menu");
                        notifyObservers(new ActionEvent(this, 0, "SaveMenu"));
                        break;
                    case OPTIONS:
                        System.out.println("Moving to Options menu");
                        notifyObservers(new ActionEvent(this, 0, "OptionsMenu"));
                        break;
                    case QUIT:
                        System.out.println("Terminating program");
                        notifyObservers(new ActionEvent(this, 0, "Exit"));
                        break;
                }
            }
        }
    }
}
