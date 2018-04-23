package view;

import static org.lwjgl.glfw.GLFW.*;
import java.nio.IntBuffer;
import org.lwjgl.*;

import model.*;
import controller.Game;

public class MainWindow extends GameWindow {

    private Game context;
    private GameWindow view;
    private long window;
    private String[] images = {"./assets/GameTitle.png",
                               "./assets/PlayButton.png",
                               "./assets/OptionsButton.png",
                               "./assets/QuitButton.png"};

    private WindowModel menuModel = new MenuModel();

    public MainWindow(Game context, GameWindow view, long window){
        this.context = context;
        this.view = view;
        this.window = window;
    }

    public void click(Double posX, Double posY) {
        ImageData images[] = menuModel.getImages();

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);

        glfwGetWindowSize(window, w, h);

        posY = (double)h.get()-posY; // inverting y-axis

        System.out.println("MClick ("+posX+", "+posY+")");

        for ( int i = 0; i<images.length;i++) {
            if(images[i].isButton()) {
                if( (posX >= images[i].getX()&& posX <= images[i].getX()+images[i].getWidth()) && (posY >= images[i].getY() && posY <= images[i].getY()+images[i].getHeight())){
                    System.out.println(images[i].getPath()+" pressed");
                    if(images[i].getPath().equals("./assets/OptionsButton.png")){
                        context.setState(new OptionsWindow(context, view, window));
                    }
                    if(images[i].getPath().equals("./assets/QuitButton.png")){
                        glfwSetWindowShouldClose(window, true);
                    }
                }
            }
        }
    }
    public void render(){
        view.paint(menuModel);
    }
}
