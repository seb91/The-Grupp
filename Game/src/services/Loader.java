package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Loads Level and Map files by translating the file text into and array of Strings.
 * This is used by the controller when creating the level model and the map view.
 *
 * @author Sebastian
 */
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
