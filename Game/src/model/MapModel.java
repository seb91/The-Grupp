package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapModel extends Model {

    ArrayList<String> map;
    ArrayList<Node> nodes = new ArrayList<>();
    Pointer pointer;

    public MapModel(String mapName) {
        int x,y;
        try {
            map = assets.getMap(mapName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < map.size(); i = i+3){
            x = Integer.parseInt(map.get(i+1));
            y = Integer.parseInt(map.get(i+2));
            if(map.get(i).equals("NODE")) {
                //To be checked with save file later
                if(true) {
                    nodes.add(new Node(Node.Id.NODE, x,y,0,0));
                } else {
                    nodes.add(new Node(Node.Id.LOCKED_NODE, x,y,0,0));
                }
            }
            if(map.get(i).equals("BOSS_NODE")) {
                //To be checked with save file later
                if(true) {
                    nodes.add(new Node(Node.Id.BOSS_NODE, x,y,0,0));
                } else {
                    nodes.add(new Node(Node.Id.LOCKED_BOSS_NODE, x,y,0,0));
                }
            }
        }
        x = Integer.parseInt(map.get(1));
        y = Integer.parseInt(map.get(2));
        pointer = new Pointer(Pointer.Id.POINTER,x+25,y+110,0,0);
        entities.add(pointer);
        entities.addAll(nodes);

        for(Entity e: entities){
            System.out.println(e);
        }
    }
    public void moveRight() {
        int position = pointer.getPosition()+1;
        if(position < nodes.size()){
            updatePosition(position);
        }
    }
    public void moveLeft() {
        int position = pointer.getPosition() - 1;
        if(position >= 0) {
            updatePosition(position);
        }
    }
    public void updatePosition(int position){
        pointer.setPosition(position);
        int x = nodes.get(position).getX() + 25;
        int y = nodes.get(position).getY() + 110;
        pointer.setX(x);
        pointer.setY(y);
    }
}

