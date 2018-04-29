package controller;

import view.MainWindow;
import view.OptionsWindow;
import view.GameWindow;
import view.LevelWindow;

import java.awt.event.ActionEvent;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Game implements Observer{

    private GameWindow view;

    private long window;

    //Temporary, for testing purposes. Should be replaced with a Level model later.
    int model = 75;

    public void run() {

        view = new MainWindow();
        view.addObserver(this);

        window = view.init();

        //Main game loop
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
            case("MainMenu"):
                System.out.println("Main menu should load");
                view = new MainWindow();
                view.addObserver(this);
                break;
            case("Play"):
                System.out.println("Level view should load");
                view = new LevelWindow(model);
                view.addObserver(this);
                break;
            case("CharacterRight"):
                model=model+5;
                view = new LevelWindow(model);
                view.addObserver(this);
                break;
            case("CharacterLeft"):
                model=model-5;
                view = new LevelWindow(model);
                view.addObserver(this);
                break;
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