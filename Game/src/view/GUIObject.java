package view;

/*
 * This object holds an ID, coordinates and a directory path value.
 *
 * @author Sebastian
 */
public class GUIObject {
    protected String imagePath;
    protected int x,y;


    public Button.Id id;
    public enum Id {
        PLAY, OPTIONS, QUIT, RETURN, ENTER, POINTER,
        NODE, LOCKED_NODE, BOSS_NODE, LOCKED_BOSS_NODE,
        SAVE1, SAVE2, SAVE3, BG_MUSIC_TOGGLE,HEALTH_BAR,MAP,MENU, RESUME
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
