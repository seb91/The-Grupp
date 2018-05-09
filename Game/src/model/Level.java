package model;

import java.util.ArrayList;

public class Level {

    private ArrayList<Entity> entities = new ArrayList<>();
    private Player player;
    private String entityType;
    private int levelWidth = 1000;
    int x,y;

    public Level(ArrayList<String> level) {

        for(int i = 0; i < level.size(); i = i+3){
            entityType = level.get(i);
            x = Integer.parseInt(level.get(i+1));
            y = Integer.parseInt(level.get(i+2));
            switch (entityType) {
                case "PLAYER":
                    player = new Player(Player.Id.PLAYER,x,y,40,75,100);
                    this.entities.add(player);
                    break;
                case "PLATFORM":
                    this.entities.add(new Terrain(Terrain.Id.PLATFORM,x,y,100,75));
                    break;
                case "GROUND":
                    this.entities.add(new Terrain(Terrain.Id.GROUND,x,y,800,75));
                    break;

            }
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void moveRight(){
        player.setFriction(0);
        player.setDx(10);
        for (Entity e : entities) {
            if(e.getId() != Entity.Id.PLAYER){
                //e.setX();
            }
        }
    }
    public void moveLeft(){
        player.setFriction(0);
        player.setDx(-10);
        for (Entity e : entities) {
            if(e.getId() != Entity.Id.PLAYER){
                //e.setX();
            }
        }
    }
    public void stopMoving(){
        player.setFriction(1);
    }

    public void jump(){
        if(player.getY()== 75){
            System.out.println("Jump");
            player.setDy(15);
        }
    }

    public int getLevelWidth(){
        return levelWidth;
    }


    public void update(){
        player.update();
    }

    public void playerAttack(){

    }

}
