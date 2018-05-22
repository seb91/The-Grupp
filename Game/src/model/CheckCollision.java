/*
 * This class checks for collision between a moving entity and other entities
 *
 */
package model;

public class CheckCollision {

    public static void collision(MovingEntity a, Entity b){

        if(b!=a) {
            boolean rightOfEntity = a.getX() >= b.getX()+b.getWidth();
            boolean leftOfEntity = a.getX()+a.getWidth() <= b.getX();

            boolean belowEntity = a.getY()+a.getHeight() <= b.getY();
            boolean aboveEntity = a.getY() >= b.getY() + b.getHeight();
            boolean withinEntityWidth = a.getX()+a.getWidth() > b.getX() && a.getX() < b.getX() + b.getWidth();

            //Checks if there would be an overlap in the next render
            if (a.overlaps(a.nextX(),a.nextY(),b)||b.overlaps(b.getX()-a.getDx(),b.getY(),a)) {
                if (a.getId() == Entity.Id.PLAYER) {
                    ((Player)a).damageCheck(b.id);
                }
                System.out.println(a.getId() + " collided with " + b.id);
                //From above
                if (aboveEntity && withinEntityWidth) {
                    a.setFallLimit(b.getY() + b.getHeight());
                    a.setStandingOn(b);
                    System.out.println("Fall limit is now " + (b.getY() + b.getHeight()));
                }
                //From below
                else if(belowEntity && withinEntityWidth) {
                    a.setDy(0);
                    a.setY(b.getY()-a.getHeight()-1);
                }
                //From the Right
                else if(rightOfEntity){
                    a.setLeftLimit(b.getX() + b.getWidth()+1);
                }
                //From the Left
                else if(leftOfEntity){
                    a.setRightLimit(b.getX()-a.getWidth()-1);
                }

            } else if(!withinEntityWidth && a.getStandingOn() == b){
                a.setFallLimit(0);
            }
        } else {
            a.setLeftLimit(0);
            // to be set to level a.getWidth() later
            a.setRightLimit(1000);
        }

    }
}