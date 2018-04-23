package model;

public class MenuModel implements WindowModel {

    //One banner and three buttons.
     private final int nrOftextures = 4;
     public float[] backgroundRBGA;
     public ImageData[] images;


    public MenuModel() {
        backgroundRBGA = new float[]{1.0f,0.0f,0.0f,0.0f};

        images = new ImageData[nrOftextures];

        int y = 500;
        int x =325;

        images[0] = new ImageData("./assets/GameTitle.png", 150, y ,500,100,false);
        images[1] = new ImageData("./assets/PlayButton.png", x, y-75,150,50,true );
        images[2] = new ImageData("./assets/OptionsButton.png", x, y-150,150,50,true);
        images[3] = new ImageData("./assets/QuitButton.png", x, y-225,150,50,true);
    }

    public float[] getBackgroundRBGA() {
        return backgroundRBGA;
    }

    public ImageData[] getImages() {
        return images;
    }
}
