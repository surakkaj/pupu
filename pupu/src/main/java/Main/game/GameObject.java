/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.graphics.Shader;
import Main.graphics.Texture;
import Main.graphics.VertexArrayObject;
import Main.utilities.Matrix4f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import Main.utilities.Vector3f;

/**
 * This class is the abstract basis of all gameobjects to clean up the code
 *
 * @author Daniel Viktor Isaac
 */
public abstract class GameObject {

    // public int vaoID;
    public int count;
    public float SIZE = 0f;

    private VertexArrayObject vao;
    private Texture tex;
    private Vector3f position = new Vector3f();
    private float rot = 0;
    private float delta = 0.0f;

    public float[] vertices, tcs;
    public byte[] indices;

    public GameObject(String texturePath) {
        if (SIZE == 0) {

            this.SIZE = 1.0f;
        }
        vertices = new float[]{
            -SIZE / 2.0f, -SIZE / 2.0f, 0f,
            -SIZE / 2.0f, SIZE / 2.0f, 0f,
            SIZE / 2.0f, SIZE / 2.0f, 0f,
            SIZE / 2.0f, -SIZE / 2.0f, 0f,};

        tcs = new float[]{
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };

        indices = new byte[]{
            0, 1, 2, 2, 3, 0
        };
        tex = new Texture(texturePath);
        Matrix4f pr_matrix = Matrix4f.ortographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -16.0f, 16.0f);
        Shader.SHADER.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.SHADER.setUniform1i("tex", 1);
        //this.vaoID = vaoID;
        this.count = indices.length;
        vao = new VertexArrayObject(this.vertices, this.indices, this.tcs);

    }
    
    

    /**
     *
     * @param size the size of the object
     * @param texturePath the texturepath where the objects texture locates
     */
    public GameObject(float size, String texturePath) {
        this.SIZE = size;
        vertices = new float[]{
            -SIZE / 2.0f, -SIZE / 2.0f, 0f,
            -SIZE / 2.0f, SIZE / 2.0f, 0f,
            SIZE / 2.0f, SIZE / 2.0f, 0f,
            SIZE / 2.0f, -SIZE / 2.0f, 0f,};

        tcs = new float[]{
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };

        indices = new byte[]{
            0, 1, 2, 2, 3, 0
        };
        tex = new Texture(texturePath);
        Matrix4f pr_matrix = Matrix4f.ortographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -16.0f, 16.0f);
        Shader.SHADER.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.SHADER.setUniform1i("tex", 1);
        //this.vaoID = vaoID;
        this.count = indices.length;
        vao = new VertexArrayObject(this.vertices, this.indices, this.tcs);

    }

    public abstract void update();

    public void draw() {

        Shader.SHADER.enable();
        Shader.SHADER.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
        tex.bind();
        vao.render();
        Shader.SHADER.disable();

    }

    public void addToRot(float a) {
        rot += a;
    }

    public void addToX(float a) {
        position.x += a;
    }

    public void addToY(float a) {
        position.y += a;
    }

    public void addToZ(float a) {
        position.z += a;
    }

    public void setX(float a) {
        position.x = a;
    }

    public void setY(float a) {
        position.y = a;
    }

    public void setZ(float a) {
        position.z = a;
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public void setPosition(Vector3f pos) {
        this.position.x = pos.x;
        this.position.y = pos.y;
        this.position.z = pos.z;
    }

    public void setSize(float size) {
        this.SIZE = size;
    }
}
