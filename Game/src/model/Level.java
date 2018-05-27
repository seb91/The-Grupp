package model;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

/*
 * Given a list of IDs, usually from a file, will generate a customized level model.
 * This model defines everything existing on the Level, as well as, functionality to modify the model.
 *
 * @author Sebastian
 * @author Eric
 * @author Alexander
 */
public class Level {

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Player player;
    private String entityType;
    private int levelWidth;
    public boolean pressedR;
    public boolean pressedL;
    public boolean levelComplete;
    int x,y,levelNr;

    public Level(ArrayList<String> level) {

        levelNr = Integer.parseInt(level.get(0));
        for(int i = 1; i < level.size(); i = i+3){
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
                    this.entities.add(new BouncingBall(BouncingBall.Id.BALL,x,y, 50,50));
                    break;
                case "BOSSBALL":
                    this.entities.add(new BouncingBossBall(BouncingBossBall.Id.BOSSBALL,x,y, 50,50));
                    break;
                case "MOVINGPLATFORM":
                    this.entities.add(new MovingPlatform(MovingPlatform.Id.MOVINGPLATFORM,x,y,100,25));
                    break;
                case "GOAL":
                    this.entities.add(new Terrain(Entity.Id.GOAL, x, y, 50, 50));
                    this.levelWidth = x;
                    CheckCollision.setLeft(0);
                    CheckCollision.setRight(levelWidth);
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
            player.setDy(15);
        }
    }

    public void update(){


        if(player.getX()+player.getWidth()+5 >= levelWidth){
            levelComplete = true;
        }

        ArrayList<Entity> copy = new ArrayList<>(entities);

        for (Entity e: copy) {
            player.collision(e);

            if(e.getId() == Entity.Id.PROJECTILE){
                Projectile p = (Projectile) e;
                p.updatePosition();
                ArrayList<Enemy> copyEnemies = new ArrayList<>(enemies);
                for(Enemy enemy : copyEnemies){
                    //enemy dies
                    if(p.overlaps(p.getX(), p.getY(), enemy)){
                        entities.remove(p);
                        entities.remove(enemy);
                        enemies.remove(enemy);
                    }
                }
            }

            for (Enemy enemy : enemies) {
                if (enemy != null) {
                    enemy.collision(e);
                }
            }
        if (e.getId() == Entity.Id.BALL){
            BouncingBall b = (BouncingBall)e;
            b.collision(player);
            b.update();
        }
            if (e.getId() == Entity.Id.BOSSBALL){
                BouncingBossBall b = (BouncingBossBall)e;
                b.collision(player);
                b.update();
            }
            if (e.getId() == Entity.Id.MOVINGPLATFORM){
                MovingPlatform p = (MovingPlatform) e;
                p.update();
                p.collision(player);
            }
        }
    for(Enemy enemy: enemies){
        enemy.move();
        enemy.update();
    }

        player.update();

    }

    public void playerAttack(){
        this.entities.add(player.fireProjectile());
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

    public int getLevelNr() {
        return levelNr;
    }
}
