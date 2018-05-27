package view;

import model.*;

import java.util.HashMap;

/*
 * Functions as a collections of directory paths for game assets(paths to files used by the program).
 *
 * It holds three HashMap objects for images used in relation to the model object Entity,
 * view objectGUIObject and view object Audio.
 *
 * A single static version of this file is only every used and is contained in GameWindow.Entity,
 * GUIObject and Audio objects all contain ID’s, these ID’s are used as a Keys in each corresponding
 * HashMap and is paired with a directory path as its Value.
 *
 * @author Sebastian
 */
public class AssetHandler {

    private HashMap<GUIObject.Id,String> menuPaths = new HashMap<>();
    private HashMap<Entity.Id,String> levelPaths = new HashMap<>();
    private HashMap<Audio.Id,String> audioPaths = new HashMap<>();

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
       menuPaths.put(Button.Id.MAP,"./assets/BackToMap.png");
       menuPaths.put(Button.Id.MENU,"./assets/BackToMenu.png");
       menuPaths.put(Button.Id.RESUME,"./assets/Resume.png");
       menuPaths.put(Button.Id.BG_MUSIC_TOGGLE,"./assets/BgMusicButton.png");
       menuPaths.put(GUIObject.Id.HEALTH_BAR,"./assets/health_bar/HealthBar1.png");

       levelPaths.put(Entity.Id.PLAYER,"./assets/characters/Player1.png");
       levelPaths.put(Entity.Id.ENEMY,"./assets/characters/Enemy1.png");
       levelPaths.put(Entity.Id.GROUND,"./assets/GroundTexture.png");
       levelPaths.put(Entity.Id.PLATFORM,"./assets/PlatformTexture.png");
       levelPaths.put(Entity.Id.MOVINGPLATFORM,"./assets/PlatformTexture.png");
       levelPaths.put(Entity.Id.BALL,"./assets/Ball.png");
       levelPaths.put(Entity.Id.BOSSBALL,"./assets/Ball.png");
       levelPaths.put(Entity.Id.PROJECTILE,"./assets/Projectile.png");
       levelPaths.put(Entity.Id.GOAL,"./assets/Goal.png");

       audioPaths.put(Audio.Id.BOUNCE,"./assets/audio/bounce.ogg");
       audioPaths.put(Audio.Id.BG_MUSIC,"./assets/audio/background_music.ogg");
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

    public void updateLevelAsset(Entity.Id key, String value){
       levelPaths.put(key,value);
    }

}
