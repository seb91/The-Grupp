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

    public void run() {

        view = new MainWindow();
        view.addObserver(this);

        view.init();
        view.loop();

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
            case("Option pressed"):
                System.out.println("option menu should appear");
                view = new OptionsWindow();
                break;
            case("Quit pressed"):
                glfwTerminate();
                glfwSetErrorCallback(null).free();
                System.exit(0);
                break;
            case("Back pressed"):
                view = new MainWindow();
                break;
        }

    }
}