package view;

/*
 * Does not use asset handler, takes a directory path in its constructor instead.
 *
 * @author Sebastian
 */
public class Image extends GUIObject {

    public Image(String imagePath, int x, int y) {
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
    }

}

