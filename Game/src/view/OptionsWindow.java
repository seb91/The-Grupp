package view;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;

import services.Texture;

public class OptionsWindow extends GameWindow {

    float[] backgroundRBGA = new float[]{0.0f,0.0f,1.0f,0.0f};

    private List<Button> buttons = new ArrayList<>();
    private List<ViewObject> viewItems = new ArrayList<>();

    public OptionsWindow(){
        buttons.add(new Button(Button.Id.RETURN,"./assets/BackButton.png", 325, 425,150,50));
        viewItems.addAll(buttons);
        viewItems.add(new Image("./assets/OptionsTitle.png", 150, 500 ,500,100));
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);

        for (ViewObject v : viewItems) {
            paint(v);
        }
    }

    @Override
    protected void click(double posX, double posY) {
        int winWidth = getWindowWidth();
        int winHeight = getWindowHeight();

        posY = winHeight - posY;

        System.out.println("MClick ("+posX+", "+posY+")");

        for ( int i = 0; i< buttons.size();i++) {
            if(buttons.get(i).check(posX,posY)) {
                switch (buttons.get(i).id) {
                    case RETURN:
                        System.out.println("Returning to Main Menu");
                        notifyObservers(new ActionEvent(this, 2, "ReturnMain"));
                        break;
                }
            }
        }
    }
}
