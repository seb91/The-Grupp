package view;

import java.awt.event.ActionEvent;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import static java.util.Arrays.asList;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import services.*;

/*
 * Abstract definition of a game window. Defines the basic functionality of all windows, such as
 * supplying them with vital methods. Each class that extends this must define their own render
 * and input logic.
 *
 * Initializes the game window by using the GLFW library, contains assets, audio and renders textures.
 *
 * This is where the background music is created and run. It also sets up key callbacks, meaning,
 * what happens when a specific key is pressed.
 *
 * All other Windows inherit this class.
 *
 * @author Eric
 * @author Sebastian
 */
public abstract class GameWindow implements Observable{

    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private Texture tex;
    private ArrayList<Listener> listeners = new ArrayList<Listener>();
    private static ArrayList<Audio> audio = new ArrayList<>();
    protected static Boolean musicOn = true;
    protected static int saveData,saveSlot;
    protected static AssetHandler assets = new AssetHandler();

    // The window handle
    private static long window;

    public long init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(windowWidth, windowHeight, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);


        //ADDED BECAUSE ELSE EVERYTHING BLOWS UP
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);

        // Enable v-sync
        glfwSwapInterval(1);

        //Add background music, and play it.
        audio.add(new AudioObject(Audio.Id.BG_MUSIC,77000,true));
        playAudio(Audio.Id.BG_MUSIC);

        // Make the window visible
        glfwShowWindow(window);

        return window;
    }

    public static void terminateAllAudio(){
        for(Audio a: audio){
            a.terminateAudio();
        }
    }

    public static void playAudio(Audio.Id id){
        for(Audio a: audio){
            if(a.id == id) {
                (new Thread(a)).start();
            }
        }
    }
    public static void terminateAudio(Audio.Id id){
        for(Audio a: audio){
            if(a.id == id){
                if(a!= null) {
                    a.terminateAudio();
                }
            }
        }
    }

    public void input(){
        //Cursor input
        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(window, x, y);

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            if ( button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_RELEASE )
                click(x.get(),y.get());
        });

        //Key input
        //http://www.glfw.org/docs/latest/group__keys.html
        List<Integer> AcceptedKeys = asList(GLFW_KEY_LEFT,
                                            GLFW_KEY_RIGHT,
                                            GLFW_KEY_ESCAPE,
                                            GLFW_KEY_ENTER,
                                            GLFW_KEY_SPACE,
                                            GLFW_KEY_Z);
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( AcceptedKeys.contains(key) && (action == GLFW_PRESS)){
                pressed(key);
            }
            if ( AcceptedKeys.contains(key) && (action == GLFW_RELEASE)){
                released(key);
            }

        });
    }
    protected void paint(String imagePath, int x, int y) {
        tex = tex.loadTexture(imagePath);

        float floatPerPixelX = 2.0f/windowWidth;
        float floatPerPixelY = 2.0f/windowHeight;

        float floatX; float floatY;
        float width; float height;

        floatX = -1.0f + floatPerPixelX*x;
        floatY = -1.0f + floatPerPixelY*y;

        width = floatPerPixelX*tex.getWidth();
        height = floatPerPixelY*tex.getHeight();

        glBegin (GL_QUADS);
        glTexCoord2f(0,0); glVertex2f(floatX,floatY);
        glTexCoord2f(0,1); glVertex2f(floatX,floatY+height);
        glTexCoord2f(1,1); glVertex2f(floatX+width,floatY+height);
        glTexCoord2f(1,0); glVertex2f(floatX+width,floatY);
        glEnd();

        tex.delete();
    }

    public abstract void render();

    protected abstract void click(double posX, double posY);

    protected void pressed(int key){};
    protected void released(int key){};

    protected int getWindowWidth(){
        return windowWidth;
    }

    protected int getWindowHeight(){
        return windowHeight;
    }

    @Override
    public void addListener(Listener o) {
        listeners.add(o);
    }

    protected void notifyListeners(ActionEvent e){
        for(Listener l : listeners){
            l.actionPerformed(e);
        }
    }
}
