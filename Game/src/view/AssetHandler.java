package view;

import model.*;

import java.util.HashMap;

public class AssetHandler {

    HashMap<GUIObject.Id,String> menuPaths = new HashMap<>();
    HashMap<Entity.Id,String> levelPaths = new HashMap<>();

   public AssetHandler() {
       menuPaths.put(Node.Id.NODE, "./assets/Node.png");
       menuPaths.put(Node.Id.LOCKED_NODE,"./assets/LockedNode.png");
       menuPaths.put(Node.Id.BOSS_NODE,"./assets/BossNode.png");
       menuPaths.put(Node.Id.LOCKED_BOSS_NODE,"./assets/LockedBossNode.png");
       menuPaths.put(Pointer.Id.POINTER,"./assets/NodePointer.png");
       menuPaths.put(Button.Id.PLAY,"./assets/PlayButton.png");
       menuPaths.put(Button.Id.OPTIONS,"./assets/OptionsButton.png");
       menuPaths.put(Button.Id.QUIT,"./assets/QuitButton.png");
       menuPaths.put(Button.Id.RETURN,"./assets/ReturnButton.png");
       menuPaths.put(Button.Id.ENTER,"./assets/EnterButton.png");

    }
    public String getMPath(GUIObject.Id key){
       return menuPaths.get(key);
    }
    public String getLPath(Entity.Id key){
        return levelPaths.get(key);
    }

}
