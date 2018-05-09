package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

    ArrayList<String> map;
    Scanner sc;

    public ArrayList<String> loadFile(String fileName) throws FileNotFoundException {
        map = new ArrayList<>();
        sc = new Scanner(new File("./assets/maps_and_levels/"+fileName+".txt"));

        while(sc.hasNext()){
            map.add(sc.next());
        }
        return map;
    }
}
