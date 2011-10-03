/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rotating.cube;

import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 *
 * @author Eric
 */
public class Main {

    public static int DISPLAY_WIDTH = 800;
    public static int DISPLAY_HEIGHT = 500;
    
    
    public static Cube c;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.create();
            main.run();
            main.destroy();
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Main() {
        c = new Cube();
        c.x = DISPLAY_WIDTH / 2;
        c.y = DISPLAY_HEIGHT / 2;
        c.width = 10.0f;
        c.xa= 0;
        c.ya = 0;
        c.za = 0;
        c.r = 1.0f;
        c.g = 0.0f;
        c.b = 0.0f;
    }
    
    public void destroy() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
    
    public void create() throws LWJGLException {
        //Display
        Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        Display.setFullscreen(false);
        Display.setTitle("Cube Draw!");
        Display.create();

        //Keyboard
        Keyboard.create();

        //Mouse
        Mouse.setGrabbed(false);
        Mouse.create();

        //OpenGL
        initGL();
        resizeGL();
    }
    
    public static void initGL() {
        glEnable(GL_LIGHTING);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
    }
    
    public static void resizeGL() {
        glViewport(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        
        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();
        
        gluPerspective(10.0f, DISPLAY_WIDTH/DISPLAY_HEIGHT, 0.0f, 100.0f);
        glOrtho(0.0f, DISPLAY_WIDTH, 0.0f, DISPLAY_HEIGHT, 0.0f, 100.0f);
        glMatrixMode(GL_MODELVIEW);
        
        glLoadIdentity();
    }
    
    public static void processKeyboard() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            c.xa--;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            c.ya--;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            c.xa++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            c.ya++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            c.z++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            c.z--;
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
            c.width++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
            c.width--;
        }
    }
    
    public static void render() {
        GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        c.render();
    }
    
    public void run() {
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if (Display.isVisible()) {
                processKeyboard();
                render();
            } else {
                if (Display.isDirty()) {
                    render();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
            Display.update();
            Display.sync(60);
        }
        System.out.println("Exit Requested.");
    }
}
