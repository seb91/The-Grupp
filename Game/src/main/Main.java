package main;

import controller.*;
import services.SaveGame;

import java.io.FileNotFoundException;

public class Main {
    public static void main (String[] args) {
        new Game().run();
    }
}
