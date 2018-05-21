package model.model_tests;

import junit.framework.TestCase;
import model.Entity;
import model.Player;
import org.junit.Test;

/*
 Test class focusing on testing functionality of the player class and some inherited functionality
 from the Entity class.
 */
public class PlayerTest extends TestCase{

    Player pl1;
    Player pl2;

    @org.junit.Before
    public void setUp() throws Exception {
        System.out.println("Setting up objects for Player test");

        int width = 5;
        int height = 5;
        int hp = 10;

        pl1 = new Player(Entity.Id.PLAYER, 10, 10, width, height, hp);
        pl2 = new Player(Entity.Id.PLAYER, -10, -10, width, height, hp);

        pl1.setDx(5);
        pl2.setDx(5);

        //set these to high values so we dont have to worry about them
        pl1.setLeftLimit(-50);
        pl1.setRightLimit(50);
        pl2.setLeftLimit(-50);
        pl2.setRightLimit(50);
    }

    /*
     Testing if players position is updated correctly every time nextX() is called.
     */
    @Test
    public void testUpdate(){
        System.out.println("Testing position update method for Player...");

        assertEquals(10, pl1.getX());
        assertEquals(-10, pl2.getX());

        //update the position of both objects 3 times
        for(int i = 0; i < 3; i++){
            pl1.update();
            pl2.update();
        }

        assertEquals(25, pl1.getX());
        assertEquals(5, pl2.getX());

        System.out.println("Test complete!\n");
    }

    /*
     Testing if our two Player objects are found to be overlapping after we move them
     within range.
     */
    @Test
    public void testOverlaps(){
        System.out.println("Testing overlaps method for Player...");

        //setting both object's dx so that they will move towards each other for
        //every update we call
        pl1.setDx(-3);
        pl1.setDy(-3);
        pl2.setDx(3);
        pl2.setDy(3);

        for(int i = 0; i < 3; i++){
            pl1.update();
            pl2.update();
        }

        //pl1 position should be 1,1 and pl2 should be -1,-1
        //meaning they intersect due to widht/height of 5
        assertTrue(pl1.overlaps(pl1.getX(), pl1.getY(), pl2));

        System.out.println("Test complete!\n");
    }

    /*
     Testing if updateHP correctly deals damage whenever called, and that it returns
     the value true if the player is killed.
     */
    @Test
    public void testUpdateHP(){
        System.out.println("Testing updateHP method for Player...");

        assertEquals(10, pl1.getHealth());

        for(int i = 0; i < 3; i++){
            assertFalse(pl1.updateHP(-3));
        }

        assertEquals(1, pl1.getHealth());
        assertTrue(pl1.updateHP(-3));

        System.out.println("Test complete!\n");
    }

    /*
     Testing if player is not hurt during invulnerability period that occurs
     after taking damage.
     */
    @Test
    public void testDamageCheck() throws InterruptedException {
        System.out.println("Testing damageCheck method for Player...");

        assertEquals(10, pl1.getHealth());

        //we wait a bit because the player is invulnerable for 1 second
        //after spawning
        Thread.sleep(1000);

        pl1.damageCheck(Entity.Id.ENEMY);
        //above damage check should have dealt 1 damage to player
        assertEquals(9, pl1.getHealth());

        Thread.sleep(500);
        pl1.damageCheck(Entity.Id.ENEMY);
        //health should remain the same
        assertEquals(9, pl1.getHealth());

        Thread.sleep(1000);
        pl1.damageCheck(Entity.Id.ENEMY);
        //now enough time has passed, so health should have been decreased
        assertEquals(8, pl1.getHealth());

        System.out.println("Test complete!");
    }
}
