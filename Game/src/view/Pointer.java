package view;

public class Pointer extends GUIObject {
    private int position;
    public Id id;
    public enum Id {
        POINTER
    }

    public Pointer(Id id, String imagePath,int x, int y, int position) {
        this.position = position;
        this.id = id;
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
    }
    public int getPosition() {
        return position;

    }

    public void setPosition(int position) {
        this.position = position;
    }
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}

}
