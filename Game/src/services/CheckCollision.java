package services;

import model.Entity;
import model.MovingEntity;

public class CheckCollision {

    public static void collision(MovingEntity a, Entity b){

        if(b!=a) {
            boolean rightOfEntity = a.getX() >= b.getX()+b.getWidth();
            boolean leftOfEntity = a.getX()+a.getWidth() <= b.getX();

            boolean belowEntity = a.getY()+a.getHeight() <= b.getY();
            boolean aboveEntity = a.getY() >= b.getY() + b.getHeight();
            boolean withinEntityWidth = a.getX()+a.getWidth() > b.getX() && a.getX() < b.getX() + b.getWidth();

            //Checks if there would be an overlap in the next render
            if (a.overlaps(a.nextX(),a.nextY(),b)||b.overlaps(b.getX()-a.getDx(),b.getY()+a.getDy(),a)) {
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
                    a.setLeftLimit(b.getX() + b.getWidth());
                }
                //From the Left
                else if(leftOfEntity){
                   a.setRightLimit(b.getX()-a.getWidth());
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
