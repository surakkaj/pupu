/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static utilities.Utilities.*;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class VertexArrayObject {
    
    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;
    
    public VertexArrayObject(float[] vertices, byte[] indices){
        createArrayObject(vertices, indices);
    }
    
    //Will handle all the vertex object creations
    public void createArrayObject(float[] vertices, byte[] indices){
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        createVerticesBuffer(vertices);
        createindicesBuffer(indices);
        
        glBindVertexArray(0);
        
        
    }
    
    private void createVerticesBuffer(float[] vertices){
        int vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    private void createindicesBuffer(byte[] indices){
        int ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createByteBuffer(indices), GL_STATIC_DRAW);
    }
    
    
    
}
