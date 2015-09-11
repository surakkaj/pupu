/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.graphics.VertexArrayObject;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import Main.utilities.Vector3f;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class GameObject {

    public int vaoID;
    public int count;
    public float SIZE = 1.0f;
    
    private VertexArrayObject mesh;
    private Vector3f position = new Vector3f();
    private float rot;
    private float delta = 0.0f;

    public float[] vertices = {
        -SIZE / 2.0f, -SIZE / 2.0f, 0f,
        -SIZE / 2.0f, SIZE / 2.0f, 0f,
        SIZE / 2.0f, SIZE / 2.0f, 0f,
        SIZE / 2.0f, -SIZE / 2.0f, 0f,
    };

    public float[] tcs = new float[]{
        0, 1,
        0, 0,
        1, 0,
        1, 1
    };

    public byte[] indices = new byte[]{
        0, 1, 2, 2, 3, 0
    };

    private VertexArrayObject texture;

    public GameObject(int vaoID) {
        this.vaoID = vaoID;
        this.count = indices.length;
        texture = new VertexArrayObject(this.vertices, this.indices, this.tcs);
    }

    public void draw() {
        glBindVertexArray(this.vaoID);
        glEnableVertexAttribArray(0);
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
