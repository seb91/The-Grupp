package services;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AssetHandler {

    HashMap<Entity.Id,String> paths = new HashMap<>();
    ArrayList<String> map = new ArrayList<>();
    Scanner sc;

    public AssetHandler() {
        paths.put(Node.Id.NODE, "./assets/Node.png");
        paths.put(Node.Id.LOCKED_NODE,"./assets/LockedNode.png");
        paths.put(Node.Id.BOSS_NODE,"./assets/BossNode.png");
        paths.put(Node.Id.LOCKED_BOSS_NODE,"./assets/LockedBossNode.png");
        paths.put(Pointer.Id.POINTER,"./assets/NodePointer.png");
    }
    public String getImagepath(Entity.Id key){
       return paths.get(key);
    }
    public ArrayList<String> getMap(String mapName) throws FileNotFoundException {
        sc = new Scanner(new File("./assets/maps/"+mapName+".txt"));

        while(sc.hasNext()){
            map.add(sc.next());
        }
        return map;
    }
}