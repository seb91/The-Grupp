package services;

import model.*;
import view.Node;
import view.Pointer;

import java.util.HashMap;

public class AssetHandler {

    HashMap<Entity.Id,String> paths = new HashMap<>();

   /* public AssetHandler() {
        paths.put(Node.Id.NODE, "./assets/Node.png");
        paths.put(Node.Id.LOCKED_NODE,"./assets/LockedNode.png");
        paths.put(Node.Id.BOSS_NODE,"./assets/BossNode.png");
        paths.put(Node.Id.LOCKED_BOSS_NODE,"./assets/LockedBossNode.png");
        paths.put(Pointer.Id.POINTER,"./assets/NodePointer.png");
    }*/
    public String getImagepath(Entity.Id key){
       return paths.get(key);
    }

}
