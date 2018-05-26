package controller;

import model.Level;
import services.Loader;
import view.*;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static services.SaveGame.createGame;

public class Game implements Listener {

    private GameWindow view;
    private Loader loader = new Loader();
    private Level model;

    private long window;

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
                view.addObserver(this);
                break;
            case("SaveMenu"):
                view = new SaveWindow();
                view.addObserver(this);
                break;
            case("Dead"):
                view = new FinishedLevelWindow(e.getID(), false);
                view.addObserver(this);
                break;
            case("Pause"):
                view = new PausedWindow(e.getID(), (LevelWindow)e.getSource());
                view.addObserver(this);
                break;
            case("Resume"):
                view = (LevelWindow)e.getSource();
                view.addObserver(this);
                ((LevelWindow)e.getSource()).resume(false);
                break;
            case("Finish"):
                view = new FinishedLevelWindow(e.getID(), true);
                view.addObserver(this);
                break;
            case("Save"):
                try {
                    view = new MapWindow(loader.loadFile("map_1"),createGame(e.getID()),e.getID());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                view.addObserver(this);
                break;
            case("EnterLevel"):
                try {
                    model = new Level(loader.loadFile("level_"+e.getID()));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                view = new LevelWindow(model);
                view.addObserver(this);
                break;
            case("OptionsMenu"):
                view = new OptionsWindow();
                view.addObserver(this);
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