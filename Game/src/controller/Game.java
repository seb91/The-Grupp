package controller;
import view.GameWindow;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import view.*;

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
        glEnable(GL_TEXTURE_2D);

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

}