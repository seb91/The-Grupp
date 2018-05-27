package view;

/*
 * Used in map to symbolize a Level entry point.
 *
 * @author Sebastian
 */
public class Node extends GUIObject {

    public Node(Id id ,int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        imagePath = GameWindow.assets.getMPath(id);
    }
}
