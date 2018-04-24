package view;

public class Button implements ViewObject {
    private String imagepath;
    private int x,y,height,width;
    public Id id;

    public enum Id {
        PLAY, OPTIONS, QUIT, RETURN
    }

    public Button(Id id, String imagepath, int x, int y, int width, int height) {
        this.id = id;
        this.imagepath = imagepath;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public boolean check(Double posX, Double posY) {
        return (posX >= x && posX <= x+width) && (posY >= y && posY <= y+height);
    }

    public String getImagepath() {
        return imagepath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
