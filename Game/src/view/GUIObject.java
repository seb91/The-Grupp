package view;

public class GUIObject {
    protected String imagePath;
    protected int x,y;


    public Button.Id id;
    public enum Id {
        PLAY, OPTIONS, QUIT, RETURN, ENTER, POINTER,
        NODE, LOCKED_NODE, BOSS_NODE, LOCKED_BOSS_NODE,
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
