package model;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class Level {

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Player player;
    private String entityType;
    private int levelWidth = 2400;
    public boolean pressedR = false;
    public boolean pressedL = false;
    int x,y;

    public Level(ArrayList<String> level) {

        for(int i = 0; i < level.size(); i = i+3){
            entityType = level.get(i);
            x = Integer.parseInt(level.get(i+1));
            y = Integer.parseInt(level.get(i+2));
            switch (entityType) {
                case "PLAYER":
                    player = new Player(Player.Id.PLAYER,x,y,40,75,5);
                    this.entities.add(player);
                    break;
                case "ENEMY":
                    enemies.add(new Enemy(Entity.Id.ENEMY,x,y,40,75,1));
                    this.entities.addAll(enemies);
                    break;
                case "PLATFORM":
                    this.entities.add(new Terrain(Terrain.Id.PLATFORM,x,y,100,25));
                    break;
                case "GROUND":
                    this.entities.add(new Terrain(Terrain.Id.GROUND,x,y,800,75));
                    break;
                case "BALL":
                    System.out.println("BALL");
                    this.entities.add(new BouncingBall(BouncingBall.Id.BALL,x,y, 50,50));
                    break;
            }
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void moveRight(){
        pressedR = true;
        player.setFriction(0);
        player.setDx(5);
    }

    public void moveLeft(){
        pressedL = true;
        player.setFriction(0);
        player.setDx(-5);
    }

    public void stopMoving(int key){
        if (pressedR && pressedL) {
            if (key==GLFW_KEY_RIGHT) {
                moveLeft();
                pressedR = false;
            } else if (key==GLFW_KEY_LEFT) {
                moveRight();
                pressedL = false;
            }
        } else {
            player.setFriction(1);
            pressedL = false;
            pressedR = false;
        }
    }

    public void jump(){
        if(player.getY()== player.getFallLimit()){
            System.out.println("Jump");
            player.setDy(15);
        }
    }

    public int getLevelWidth(){
        return levelWidth;
    }

    public void update(){

    for (Entity e: entities) {
        player.collision(e);
        if (e.getId() == Entity.Id.BALL) {
            BouncingBall b = (BouncingBall) e;
            b.update();
            for (Enemy enemy : enemies) {
                if (enemy != null) {
                    enemy.collision(e);
                }
            }
        }
    }
    for(Enemy enemy: enemies){
        enemy.move();
        enemy.update();
    }

        player.update();

    }

    public void playerAttack(){
    }
    
    public int getPlayerX(){
        return player.getX();
    }

    public int getPlayerY(){
        return player.getY();
    }

    public int getPlayerHealth() {
        return player.getHealth();
    }

}
