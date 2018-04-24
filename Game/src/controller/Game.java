package controller;
import view.GameWindow;
import org.lwjgl.opengl.GL;
import view.MainWindow;
import view.OptionsWindow;

import java.awt.event.ActionEvent;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Game implements Observer{

    private GameWindow view;

    private long window;


    public void run() {

        view = new MainWindow();
        view.addObserver(this);

        window = view.init();

        while ( !glfwWindowShouldClose(window) ) {
            view.input();
            view.render();
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);


        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
            case("OptionsMenu"):
                System.out.println("option menu should appear");
                view = new OptionsWindow();
                view.addObserver(this);
                break;

            case("ReturnMain"):
                System.out.println("got to back pressed switch");
                view = new MainWindow();
                view.addObserver(this);
                break;

            case("Exit"):
                glfwTerminate();
                glfwSetErrorCallback(null).free();
                System.exit(0);
                break;
        }

    }
}