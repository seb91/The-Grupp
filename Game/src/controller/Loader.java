package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

    ArrayList<String> map = new ArrayList<>();
    Scanner sc;

    public ArrayList<String> getMap(String mapName) throws FileNotFoundException {
        sc = new Scanner(new File("./assets/maps/"+mapName+".txt"));

        while(sc.hasNext()){
            map.add(sc.next());
        }
        return map;
    }
}
