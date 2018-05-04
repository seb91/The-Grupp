package view;

public class GUIObject {
    protected String imagePath;
    protected int x,y;


    public Button.Id id;
    public enum Id {
        PLAY, OPTIONS, QUIT, RETURN, ENTER, POINTER,
        NODE, LOCKED_NODE, BOSS_NODE, LOCKED_BOSS_NODE,
        SAVE1, SAVE2, SAVE3
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*public boolean overlaps(int x, int y){

        /*int left = this.posX;
        int right = this.posX+this.width;
        int bottom = this.posY;
        int top = this.posY+height;

            return  ((this.x > e.getX() && this.x < e.getX()+e.getWidth()) && (this.posY+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                    ((this.x > e.getX() && this.x < e.getX()+e.getWidth()) && (this.y < e.getY()+e.getWidth()) && (this.posY > e.getY())) ||
                    ((this.x+this.width < e.getX()+e.getWidth() && this.x+this.width > e.getX()) && (this.y+height > e.getY()) && (this.posY+height < e.getY()+e.getHeight())) ||
                    ((this.x+this.width < e.getX()+e.getWidth() && this.x+this.width > e.getX()) && (this.y < e.getY()+e.getWidth()) && (this.posY > e.getY()));

    }*/
}
