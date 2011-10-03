/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rotating.cube;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Renderable;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Eric
 */
public class Cube implements Renderable{

    int x, y, z;
    float width;
    float r, g, b;
    float xa, ya, za;
    
    @Override
    public void render() {
        glLoadIdentity();
        glTranslatef(x, y, z);
        
        //Cube Rotation
        glRotatef(xa, 1.0f, 0.0f, 0.0f);
        glRotatef(ya, 0.0f, 1.0f, 0.0f);
        glRotatef(za, 0.0f, 0.0f, 1.0f);
        
        glBegin(GL_QUADS);
            glColor3f(r, g, b);    // Green
            glVertex3f( width, width, -width); // Top-right of the quad (Top)
            glVertex3f(-width, width, -width); // Top-left of the quad (Top)
            glVertex3f(-width, width,  width); // Bottom-left of the quad (Top)
            glVertex3f( width, width,  width); // Bottom-right of the quad (Top)
   
            //glColor3f(r, g + 1.0f, b);
            glVertex3f( width, -width,  width); // Top-right of the quad (Bottom)
            glVertex3f(-width, -width,  width); // Top-left of the quad (Bottom)
            glVertex3f(-width, -width, -width); // Bottom-left of the quad (Bottom)
            glVertex3f( width, -width, -width); // Bottom-right of the quad (Bottom)
   
            //glColor3f(r, g, b + 1.0f);
            glVertex3f( width,  width, width);  // Top-right of the quad (Front)
            glVertex3f(-width,  width, width);  // Top-left of the quad (Front)
            glVertex3f(-width, -width, width);  // Bottom-left of the quad (Front)
            glVertex3f( width, -width, width);  // Bottom-right of the quad (Front)
   
            glVertex3f( width, -width, -width); // Bottom-left of the quad (Back)
            glVertex3f(-width, -width, -width); // Bottom-right of the quad (Back)
            glVertex3f(-width,  width, -width); // Top-right of the quad (Back)
            glVertex3f( width,  width, -width); // Top-left of the quad (Back)
   
            glVertex3f(-width,  width,  width); // Top-right of the quad (Left)
            glVertex3f(-width,  width, -width); // Top-left of the quad (Left)
            glVertex3f(-width, -width, -width); // Bottom-left of the quad (Left)
            glVertex3f(-width, -width,  width); // Bottom-right of the quad (Left)
   
            glVertex3f( width,  width, -width); // Top-right of the quad (Right)
            glVertex3f( width,  width,  width); // Top-left of the quad (Right)
            glVertex3f( width, -width,  width); // Bottom-left of the quad (Right)
            glVertex3f( width, -width, -width);
        glEnd();
        //glTranslatef(-x, -y, -1);
        
    }
    
}
