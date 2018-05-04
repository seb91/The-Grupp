package view;

public class Button extends GUIObject {

    public int width,height;

    public Button(Id id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        imagePath = GameWindow.assets.getMPath(id);
    }

    public boolean check(Double posX, Double posY) {
        return (posX >= x && posX <= x+width) && (posY >= y && posY <= y+height);
    }
    
}
