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

public class Game {

    public Game() {
    }

    private GameWindow view;
    private long window;

    private GameState state;


    public void run() {

        view = new GameWindow();
        window = view.init();

        state = new MainMenu(this, view, window);

        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void loop() {
        GL.createCapabilities();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            state.input();
            state.update();
            state.render();
        }
    }

    public void setState(GameState state){
        this.state = state;
    }

    /*private void input(){

        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(window, x, y);

        if(x.get() >= 400){
            System.out.println("now option");
        }

        //x.rewind();

        //System.out.println(x.get());

    }*/
}