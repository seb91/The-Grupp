package view;

public class Image implements ViewObject {
    private String imagepath;
    private int x,y,height,width;

    public Image(String imagepath, int x, int y, int width,int height) {
        this.imagepath = imagepath;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public String getImagepath() {
        return imagepath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

