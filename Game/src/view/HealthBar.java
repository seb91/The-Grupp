package view;

import model.Player;

import static view.GameWindow.assets;

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