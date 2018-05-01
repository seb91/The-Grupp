package model;

import java.util.ArrayList;

public class Pointer extends Entity {
    private ArrayList<Integer> positions;
    private int position;

    public Pointer(Id id,int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.position = 0;
    }
    public int getPosition() {
        return position;

    }

    public void setPosition(int position) {
        this.position = position;
    }
}
