/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static Main.utilities.Utilities.*;

/**
 * This class creates the vertex array objects neccessary for rendering images.
 * @author Daniel Viktor Isaac
 */
public class VertexArrayObject {
    
    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;
    private int vaoID, vboID, iboID, tboID;
    private int count;
    
    public VertexArrayObject(float[] vertices, byte[] indices, float[] textureCoordinates){
        createArrayObject(vertices, indices,textureCoordinates);
        count = indices.length;
    }
    
    //Will handle all the vertex object creations
    public void createArrayObject(float[] vertices, byte[] indices, float[] textureCoordinates){
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);
        createVerticesBuffer(vertices);
        createindicesBuffer(indices);
        createTextureBuffer(textureCoordinates);
        glBindVertexArray(0);
        
        
        
    }
    
    private void createVerticesBuffer(float[] vertices){
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glEnableVertexAttribArray(VERTEX_ATTRIB);
    }
    
     private void createTextureBuffer(float[] textureCoordinates){
        tboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tboID);
        glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glEnableVertexAttribArray(TCOORD_ATTRIB);
    }
    
    private void createindicesBuffer(byte[] indices){
        iboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createByteBuffer(indices), GL_STATIC_DRAW);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    
    public void draw() {
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
    }
    
    public void bind(){
        glBindVertexArray(vaoID);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
    }
    public void unbind(){
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    public void render(){
        bind();
        draw();
    }
    
    
}
