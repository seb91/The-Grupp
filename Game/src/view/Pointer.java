package view;

/*
 * Unlike other GUI Objects, this hold functionality for updating position in relation to node position.
 *
 * @author Sebastian
 */
public class Pointer extends GUIObject {
    private int position;
    public Id id;

    public Pointer(Id id ,int x, int y, int position) {
        this.position = position;
        this.id = id;
        this.x = x;
        this.y = y;
        imagePath = GameWindow.assets.getMPath(id);
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
