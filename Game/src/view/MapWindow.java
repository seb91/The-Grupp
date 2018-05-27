package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/*
 * This class is generated based on save and map data and contains as many nodes as the map data instructs,
 * these nodes are either locked or unlocked based on the save data.
 *
 * It holds a pointer object, this object is rendered above a node and is moved given a key press.
 * How far the pointer may move is limited by either the end of the row of nodes,
 * or if the next node is locked.
 *
 * @author Sebastian
 */
public class MapWindow extends GameWindow {

    private float[] backgroundRBGA = new float[]{0.4f, 0.7f, 0.3f, 0.0f};

    private List<Button> buttons = new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();
    private Pointer pointer;

    public MapWindow(ArrayList<String> map,int saveData,int saveSlot) {
        //Setting up map nodes based on Save data and the map parameter.
        GameWindow.saveData = saveData;
        GameWindow.saveSlot = saveSlot;
        for(int i = 0; i < map.size(); i = i+3){

            int x = Integer.parseInt(map.get(i + 1));
            int y = Integer.parseInt(map.get(i + 2));
            if(map.get(i).equals("NODE")) {
                //To be checked with save file later
                if(saveData >= i/3) {
                    nodes.add(new Node(Node.Id.NODE, x, y));
                } else {
                    nodes.add(new Node(Node.Id.LOCKED_NODE, x, y));
                }
            }
            if(map.get(i).equals("BOSS_NODE")) {
                //To be checked with save file later
                if(saveData >= i/3) {
                    nodes.add(new Node(Node.Id.BOSS_NODE, x, y));
                } else {
                    nodes.add(new Node(Node.Id.LOCKED_BOSS_NODE, x, y));
                }
            }
        }
        //Map pointer
        pointer = new Pointer(Pointer.Id.POINTER,nodes.get(0).getX()+25,nodes.get(0).getY()+110,0);

        //Buttons
        buttons.add(new Button(Button.Id.ENTER, 0, 0, 150, 50));
        buttons.add(new Button(Button.Id.RETURN, 650, 0, 150, 50));

        // Adding all GUI objects to a mutual collection.
        viewItems.add(pointer);
        viewItems.addAll(buttons);
        viewItems.addAll(nodes);
    }

    //Renders all visual objects located in arraylist viewItems, sets background color
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(backgroundRBGA[0], backgroundRBGA[1], backgroundRBGA[2], backgroundRBGA[3]);
        for (GUIObject v : viewItems) {
            paint(v.getImagePath(),v.getX(),v.getY());
        }
    }

    protected void click(double posX, double posY) {
        int winHeight = getWindowHeight();
        posY = winHeight - posY;

        for(Button b: buttons){
            if (b.check(posX, posY)) {
                switch (b.id) {
                    case ENTER:
                        notifyListeners(new ActionEvent(this, pointer.getPosition(), "EnterLevel"));
                        break;
                    case RETURN:
                        notifyListeners(new ActionEvent(this, 0, "MainMenu"));
                        break;
                }
            }
        }
    }
    protected void pressed(int key) {
        switch (key) {
            case GLFW_KEY_LEFT:
                moveLeft();
                break;
            case GLFW_KEY_RIGHT:
                moveRight();
                break;
            case GLFW_KEY_ENTER:
                notifyListeners(new ActionEvent(this, pointer.getPosition(), "EnterLevel"));
                break;
            case GLFW_KEY_ESCAPE:
                notifyListeners(new ActionEvent(this, 0, "MainMenu"));
                break;
        }
    }
    //Relays pointer position to controller.
    public int getPosition() {
        return pointer.getPosition();
    }

    //Pointer positioning
    private void moveRight() {
        int position = pointer.getPosition()+1;
        if(position < nodes.size() && saveData >= position){
            updatePosition(position);
        }
    }
    private void moveLeft() {
        int position = pointer.getPosition() - 1;
        if(position >= 0) {
            updatePosition(position);
        }
    }
    private void updatePosition(int position){
        pointer.setPosition(position);
        int x = nodes.get(position).getX() + 25;
        int y = nodes.get(position).getY() + 110;
        pointer.setX(x);
        pointer.setY(y);
        render();
    }

}
