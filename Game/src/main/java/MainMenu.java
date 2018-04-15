import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.*;

import java.nio.*;



public class MainMenu implements GameState {

    private Game context;

    private GameWindow view;
    private long window;

    private WindowModel menuModel = new MenuModel();

    public MainMenu(Game context, GameWindow view, long window){
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
                    if(images[i].getPath().equals("./OptionsButton.png")){
                        context.setState(new OptionMenu(context, view, window));
                    }
                }
            }
        }
    }

    public void update(){

    }

    public void render(){
        view.paint(menuModel);
    }
}
