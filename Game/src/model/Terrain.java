package model;

/*
 * A simple object used for statically places Terrain, such as non-moving platforms,
 * the ground or other obstacles.
 *
 * @author Sebastian
 */
public class Terrain extends Entity {

    public Terrain(Entity.Id id, int posX, int posY, int width, int height) {
        super(id,posX, posY, width, height);
    }
}
