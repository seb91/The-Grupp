import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GameWindow {

    private final int windowWidth = 800;
    private final int windowHeight = 600 ;

    public GameWindow() {
    }
    // The window handle
    private long window;

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
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        return window;
    }

    public void paint(WindowModel model){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        Texture tex = new Texture();

        float[] bg = model.getBackgroundRBGA();
        glClearColor(bg[0], bg[1], bg[2], bg[3]);

        ImageData[] images = model.getImages();
        int x; int y;

        for (int i = 0; i < images.length; i++) {

            tex = tex.loadTexture(images[i].getPath());
            x = images[i].getX();
            y = images[i].getY();

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
        }

        glfwSwapBuffers(window);
        glfwPollEvents();
    }


}
