package model;

public class OptionsModel implements WindowModel {

    //One banner and three buttons.
    private final int nrOftextures = 2;
    public float[] backgroundRBGA;
    public ImageData[] images;


    public OptionsModel() {
        backgroundRBGA = new float[]{0.0f,0.0f,1.0f,0.0f};

        images = new ImageData[nrOftextures];

        int y = 500;
        int x =325;

        images[0] = new ImageData("./assets/OptionsTitle.png", 150, y,500,100 ,false);
        images[1] = new ImageData("./assets/BackButton.png", x, y-75,150,50 ,true);
    }

    public float[] getBackgroundRBGA() {
        return backgroundRBGA;
    }

    public ImageData[] getImages() {
        return images;
    }
}
