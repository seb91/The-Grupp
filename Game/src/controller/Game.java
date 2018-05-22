package controller;

import services.Loader;
import services.SaveGame;
import view.*;
import model.*;

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
                System.out.println("Main menu should load");
                view = new MainWindow();
                view.addObserver(this);
                break;
            case("SaveMenu"):
                System.out.println("Save menu should load");
                view = new SaveWindow();
                view.addObserver(this);
                break;
            case("Save"):
                System.out.println("Map view should load with saved progress from save slot: "+e.getID());
                try {
                    view = new MapWindow(loader.loadFile("map_1"),createGame(e.getID()));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                view.addObserver(this);
                break;
            case("EnterLevel"):
                System.out.println("Level view should load");
                try {
                    System.out.println("level_"+e.getID());
                    model = new Level(loader.loadFile("level_"+e.getID()));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                view = new LevelWindow(model);
                view.addObserver(this);
                break;
            case("OptionsMenu"):
                System.out.println("option menu should appear");
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