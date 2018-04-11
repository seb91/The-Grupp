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

    //placeholder
    private Object[] menuModel;



    public MainMenu(Game context, GameWindow view, long window){
        this.context = context;
        this.view = view;
        this.window = window;
    }

    public void input(){
        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(window, x, y);

        if(x.get() <= 400){
            System.out.println("now option");
            context.setState(new OptionMenu(context, view, window));
        }
    }

    public void update(){

    }


    public void render(){
        view.paint(menuModel);
        view.setBackgroundColor(1.0f, 0.0f, 0.0f, 0.0f);
    }
}
