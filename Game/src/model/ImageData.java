package model;

public class ImageData {
    private String path;
    private int x;
    private int y;
    private int width;
    private int height;
    private Boolean isButton;

    public ImageData(String path, int x, int y, int width, int height, Boolean isButton) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isButton = isButton;
    }

    public String getPath() {
        return path;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Boolean isButton() {
        return isButton;
    }
}
