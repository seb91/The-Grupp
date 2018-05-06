package view;

public class Node extends GUIObject {
    public Id id;
    public enum Id {
        NODE, LOCKED_NODE, BOSS_NODE, LOCKED_BOSS_NODE,
    }
    public Node(Id id,String imagePath,int x, int y) {
        this.id = id;
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
    }
}
