/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import graphics.VertexArrayObject;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class GameObject {
    
    
    public int vaoID;
    public int count;
    public float SIZE = 1.0f;
    
    float[] vertices = {
        -0.20f, 0.20f, 0f,
        -0.15f, -0.25F, 0f,
        0.25f, -0.25f, 0f,
        0.25f, 0.25f, 0f
    };
    
    public byte[] indices = new byte[] {
        0, 1, 2, 2, 3, 0
    };
    
    private VertexArrayObject vao;
    
    public GameObject(int vaoID){
        this.vaoID = vaoID;
        this.count = indices.length;
        vao = new VertexArrayObject(this.vertices, this.indices);
    }
    
    public void draw(){
        glBindVertexArray(this.vaoID);
        glEnableVertexAttribArray(0);
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
