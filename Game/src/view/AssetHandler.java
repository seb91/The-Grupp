package view;

import model.*;

import java.util.HashMap;

public class AssetHandler {

    HashMap<GUIObject.Id,String> menuPaths = new HashMap<>();
    HashMap<Entity.Id,String> levelPaths = new HashMap<>();
    HashMap<Audio.Id,String> audioPaths = new HashMap<>();

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
       menuPaths.put(Button.Id.SAVE1,"./assets/Save1.png");
       menuPaths.put(Button.Id.SAVE2,"./assets/Save2.png");
       menuPaths.put(Button.Id.SAVE3,"./assets/Save3.png");

       levelPaths.put(Entity.Id.PLAYER,"./assets/CharacterTexture.png");
       levelPaths.put(Entity.Id.GROUND,"./assets/GroundTexture.png");
       levelPaths.put(Entity.Id.PLATFORM,"./assets/PlatformTexture.png");

       audioPaths.put(Audio.Id.BOUNCE,"./assets/bounce.ogg");
    }
    public String getMPath(GUIObject.Id key){
       return menuPaths.get(key);
    }
    public String getEPath(Entity.Id key){
        return levelPaths.get(key);
    }
    public String getAPath(Audio.Id key){
        return audioPaths.get(key);
    }

}
