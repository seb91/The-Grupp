package view;

public class Button extends GUIObject {

    public int width,height;
    public Id id;
    public enum Id {
        PLAY, OPTIONS, QUIT, RETURN, ENTER, MENU,SAVE1,SAVE2,SAVE3
    }

    public Button(Id id, String imagePath, int x, int y, int width, int height) {
        this.id = id;
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean check(Double posX, Double posY) {
        return (posX >= x && posX <= x+width) && (posY >= y && posY <= y+height);
    }
    
}
