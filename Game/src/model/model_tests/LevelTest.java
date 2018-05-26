package model.model_tests;

import junit.framework.TestCase;
import model.Level;
import org.junit.Test;
import services.Loader;

import java.io.FileNotFoundException;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;

public class LevelTest extends TestCase {

    Loader loader = new Loader();
    Level level;

    @org.junit.Before
    public void setUp()  {
        System.out.println("Setting up objects for Level test");

        try {
            //loads level 1, where player spawns at x = 100;
            level = new Level(loader.loadFile("level_1"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     Test so that the level's player position is updated correctly when
     moveRight is called.
     */
    @Test
    public void testMoveRight(){
        System.out.println("Testing move right method for Level...");
        assertEquals(100, level.getPlayerX());

        //increases player dx with 5
        level.moveRight();

        level.update();

        assertEquals(105, level.getPlayerX());
        System.out.println("Test complete!\n");
    }

    /*
     Test so that the level's player position is updated correctly when
     moveLeft is called.
     */
    @Test
    public void testMoveLeft(){
        System.out.println("Testing move left method for Level...");
        assertEquals(100, level.getPlayerX());

        //decreases player dx with 5
        level.moveLeft();

        level.update();

        assertEquals(95, level.getPlayerX());
        System.out.println("Test complete!\n");
    }

    /*
     Test so that the player has friction correctly applied when
     it stops inputing a movement command.
     */
    @Test
    public void testStopMoving(){
        System.out.println("Testing stop moving method for Level...");
        assertEquals(100, level.getPlayerX());

        //decreases player dx with 5
        level.moveLeft();

        level.update();

        assertEquals(95, level.getPlayerX());

        //incurs friction, which will eventually halt our character
        level.stopMoving(GLFW_KEY_LEFT);

        //since player is affected by friction, they will halt after 5 update cycles
        for(int i = 0; i < 4; i++){
            level.update();
        }

        assertEquals(85, level.getPlayerX());

        //calling these updates should not affect player position, since dx is now 0
        //due to friction
        for(int i = 0; i < 2; i++){
            level.update();
        }

        assertEquals(85, level.getPlayerX());

        System.out.println("Test complete!\n");
    }

    /*

     */
    @Test
    public void testJump(){
        System.out.println("Testing jumping method for Level...");
        assertEquals(75, level.getPlayerY());

        //since player spawns in air we need to call update once
        level.update();

        level.jump();

        //after jump we've moved 15 units into the air
        level.update();

        assertEquals(90, level.getPlayerY());

        //after 30 updates we should be back at the ground
        for(int i = 0; i < 30; i++){
            level.update();
        }

        assertEquals(75, level.getPlayerY());

        System.out.println("Test complete!\n");
    }
}
