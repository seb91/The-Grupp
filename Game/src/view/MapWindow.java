package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MapWindow extends GameWindow {

    float[] backgroundRBGA = new float[]{0.4f, 0.7f, 0.3f, 0.0f};

    private List<Button> buttons = new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();
    private List<GUIObject> viewItems = new ArrayList<>();
    private ArrayList<String> map;
    private Pointer pointer;
    int x,y;

    public MapWindow(ArrayList<String> map) {
        //Setting up map nodes based on Save data and the map parameter.
        this.map = map;
        for(int i = 0; i < map.size(); i = i+3){
            x = Integer.parseInt(map.get(i+1));
            y = Integer.parseInt(map.get(i+2));
            if(map.get(i).equals("NODE")) {
                //To be checked with save file later
                if(true) {
                    nodes.add(new Node(Node.Id.NODE,"./assets/Node.png", x,y));
                } else {
                    nodes.add(new Node(Node.Id.LOCKED_NODE,"./assets/LockedNode.png", x,y));
                }
            }
            if(map.get(i).equals("BOSS_NODE")) {
                //To be checked with save file later
                if(true) {
                    nodes.add(new Node(Node.Id.BOSS_NODE,"./assets/BossNode.png", x,y));
                } else {
                    nodes.add(new Node(Node.Id.LOCKED_BOSS_NODE,"./assets/LockedBossNode.png", x,y));
                }
            }
        }
        //Map pointer
        pointer = new Pointer(Pointer.Id.POINTER,"./assets/NodePointer.png",nodes.get(0).getX()+25,nodes.get(0).getY()+110,0);

        //Buttons
        buttons.add(new Button(Button.Id.ENTER, "./assets/EnterButton.png", 0, 0, 150, 50));
        buttons.add(new Button(Button.Id.RETURN, "./assets/ReturnButton.png", 650, 0, 150, 50));

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
        System.out.println("LClick (" + posX + ", " + posY + ")");

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).check(posX, posY)) {
                switch (buttons.get(i).id) {
                    case ENTER:
                        System.out.println("Moving to level");
                        notifyObservers(new ActionEvent(this, 0, "EnterLevel"));
                        break;
                    case RETURN:
                        System.out.println("Moving to main menu");
                        notifyObservers(new ActionEvent(this, 0, "MainMenu"));
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
                notifyObservers(new ActionEvent(this, 0, "EnterLevel"));
                break;
            case GLFW_KEY_ESCAPE:
                notifyObservers(new ActionEvent(this, 0, "MainMenu"));
                break;
        }
    }

    //Pointer positioning
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
        render();
    }
}
