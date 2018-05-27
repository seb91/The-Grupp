package view;

import static view.GameWindow.assets;

/*
 * Holds functionality to change what Value is used in asset handler for its ID, based on model.
 *
 * @author Sebastian
 */
public class HealthBar extends GUIObject {

    public HealthBar(Id id,int x, int y) {
        this.x = x;
        this.y = y;
        this.id =id;
        imagePath= assets.getMPath(id);
    }

    public void updateHealthBar(int hp) {
        String path = assets.getMPath(GUIObject.Id.HEALTH_BAR);
        path = path.substring(0, path.length() - 5);
        imagePath = path +hp+".png";

    }
}