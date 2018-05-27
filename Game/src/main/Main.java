package main;

import controller.Game;

/*
 * Responsible for initiating the program.
 * It does so by simply creating and starting the controller, which then takes over.
 *
 * @author Sebastian
 */
public class Main {
    public static void main (String[] args) {
        new Game().run();
    }
}
