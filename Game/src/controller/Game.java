package controller;

import model.Level;
import services.Loader;
import view.*;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static services.SaveGame.createGame;

/*
 * Acts as a controller for the program, containing both the active model and the active view.
 * Game initializes the first view, the Main menu.
 * It listens to the active view waiting to get notified to update it.
 * Game will only create and use the model when creating a level view.
 *
 * @author Eric
 * @author Sebastian
 */
public class Game implements Listener {

    private GameWindow view;
    private Loader loader = new Loader();
    private Level model;

    private long window;

    public void run() {
        view = new MainWindow();
        view.addListener(this);

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
        view.terminateAllAudio();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case("MainMenu"):
                view = new MainWindow();
                view.addListener(this);
                break;
            case("SaveMenu"):
                view = new SaveWindow();
                view.addListener(this);
                break;
            case("Dead"):
                view = new FinishedLevelWindow(e.getID(), false);
                view.addListener(this);
                break;
            case("Pause"):
                view = new PausedWindow(e.getID(), (LevelWindow)e.getSource());
                view.addListener(this);
                break;
            case("Resume"):
                view = (LevelWindow)e.getSource();
                view.addListener(this);
                ((LevelWindow)e.getSource()).resume(false);
                break;
            case("Finish"):
                view = new FinishedLevelWindow(e.getID(), true);
                view.addListener(this);
                break;
            case("Save"):
                try {
                    view = new MapWindow(loader.loadFile("map_1"),createGame(e.getID()),e.getID());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                view.addListener(this);
                break;
            case("EnterLevel"):
                try {
                    model = new Level(loader.loadFile("level_"+e.getID()));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                view = new LevelWindow(model);
                view.addListener(this);
                break;
            case("OptionsMenu"):
                view = new OptionsWindow();
                view.addListener(this);
                break;
            case("Exit"):
                view.terminateAllAudio();
                glfwTerminate();
                glfwSetErrorCallback(null).free();
                System.exit(0);
                break;

        }

    }
}