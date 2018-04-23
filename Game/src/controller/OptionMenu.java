package controller;

import java.nio.IntBuffer;
import model.*;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.*;

import java.nio.*;

import view.*;


public class OptionMenu implements GameState {

    private Game context;

    private GameWindow view;
    private long window;

    private WindowModel optionsModel = new OptionsModel();

    public OptionMenu(Game context, GameWindow view, long window){
        this.context = context;
        this.view = view;
        this.window = window;
    }

    public void input(){
        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(window, x, y);

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            if ( button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_RELEASE )
                click(x.get(),y.get());
        });
    }

    private void click(Double posX, Double posY) {

        ImageData images[] = optionsModel.getImages();

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);

        glfwGetWindowSize(window, w, h);

        posY = (double)h.get()-posY; // inverting y-axis

        System.out.println("OClick ("+posX+", "+posY+")");

        for ( int i = 0; i<images.length;i++) {
            if(images[i].isButton()) {
                if( (posX >= images[i].getX()&& posX <= images[i].getX()+images[i].getWidth()) && (posY >= images[i].getY() && posY <= images[i].getY()+images[i].getHeight())){
                    System.out.println(images[i].getPath()+" pressed");
                    if(images[i].getPath().equals("./assets/BackButton.png")){
                        context.setState(new MainMenu(context, view, window));
                    }
                }
            }

        }
    }

    public void update(){

    }

    public void render(){
        view.paint(optionsModel);
    }
}
