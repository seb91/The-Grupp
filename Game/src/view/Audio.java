package view;

/*
 * Mainly serves to limit dependencies for GameWindow, AudioObject and AssetHandler.
 * Sets up audio Id's, inherits Thread and is inherited by AudioObject.
 *
 * @author Sebastian
 */
public class Audio extends Thread {

    public Id id;

    public enum Id {
        BOUNCE, BG_MUSIC
    }

    public void run(){}
    public void terminateAudio(){}
}
