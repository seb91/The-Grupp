package model;

import java.util.ArrayList;

public class Pointer extends Entity {
    private ArrayList<Integer> positions;
    private int position;

    public Pointer(Id id,int posX, int posY, int width, int height) {
        super(id,posX, posY, width, height);
        this.position = 0;
    }
    public int getPosition() {
        return position;

    }

    public void setPosition(int position) {
        this.position = position;
    }
}
