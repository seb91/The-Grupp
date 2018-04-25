package view;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.event.ActionEvent;
import java.nio.IntBuffer;
import org.lwjgl.*;

import model.*;
import controller.Game;
import services.Texture;

public class MainWindow extends GameWindow {

    int y = 500;
    int x =325;

    float[] backgroundRBGA = new float[]{1.0f,0.0f,0.0f,0.0f};

    ImageData[] images = {
            new ImageData("./assets/GameTitle.png", 150, y ,500,100,false),
            new ImageData("./assets/PlayButton.png", x, y-75,150,50,true ),
            new ImageData("./assets/OptionsButton.png", x, y-150,150,50,true),
            new ImageData("./assets/QuitButton.png", x, y-225,150,50,true)
    };

    public void paint(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        tex = new Texture();

        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);

        int x; int y;

        for (int i = 0; i < images.length; i++) {

            tex = tex.loadTexture(images[i].getPath());
            x = images[i].getX();
            y = images[i].getY();

            float floatPerPixelX = 2.0f/getWindowWidth();
            float floatPerPixelY = 2.0f/getWindowHeight();

            float floatX; float floatY;
            float width; float height;

            floatX = -1.0f + floatPerPixelX*x;
            floatY = -1.0f + floatPerPixelY*y;

            width = floatPerPixelX*tex.getWidth();
            height = floatPerPixelY*tex.getHeight();

            glBegin (GL_QUADS);
            glTexCoord2f(0,0); glVertex2f(floatX,floatY);
            glTexCoord2f(0,1); glVertex2f(floatX,floatY+height);
            glTexCoord2f(1,1); glVertex2f(floatX+width,floatY+height);
            glTexCoord2f(1,0); glVertex2f(floatX+width,floatY);
            glEnd();
        }
    }

    @Override
    protected void click(double posX, double posY) {
        int winWidth = getWindowWidth();
        int winHeight = getWindowHeight();

        posY = winHeight - posY;

        System.out.println("MClick ("+posX+", "+posY+")");

        for ( int i = 0; i<images.length;i++) {
            if(images[i].isButton()) {
                if( (posX >= images[i].getX()&& posX <= images[i].getX()+images[i].getWidth()) && (posY >= images[i].getY() && posY <= images[i].getY()+images[i].getHeight())){
                    System.out.println(images[i].getPath()+" pressed");
                    if(images[i].getPath().equals("./assets/OptionsButton.png")){
                        notifyObservers(new ActionEvent(this, 0, "Option pressed"));
                    }
                    if(images[i].getPath().equals("./assets/QuitButton.png")){
                        notifyObservers(new ActionEvent(this, 1, "Quit pressed"));
                    }
                }
            }
        }
    }
}
