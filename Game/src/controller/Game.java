package controller;

import services.Loader;
import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Game implements Listener {

    private GameWindow view;
    private Loader loader = new Loader();

    private long window;

    //Temporary, for testing purposes. Should be replaced with a Level model later.
    int modelTemp = 75;

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
            case("Map"):
                System.out.println("Map view should load");
                try {
                    view = new MapWindow(loader.getMap("map_1"));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                view.addObserver(this);
                break;
            case("EnterLevel"):
                System.out.println("Level view should load");
                view = new LevelWindow(modelTemp);
                view.addObserver(this);
                break;
            case("CharacterRight"):
                modelTemp=modelTemp+5;
                view = new LevelWindow(modelTemp);
                view.addObserver(this);
                break;
            case("CharacterLeft"):
                modelTemp=modelTemp-5;
                view = new LevelWindow(modelTemp);
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