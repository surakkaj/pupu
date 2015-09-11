/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.graphics;

import static org.lwjgl.opengl.GL20.*;
import Main.utilities.Matrix4f;
import Main.utilities.Utilities;
import Main.utilities.Vector3f;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Shader {
    
    private int ID;
    
    
    public Shader(String vertex, String fragment) {
        ID = Utilities.loadShader(vertex, fragment);
    }
    
    public static void loadAll(){
        
    }
    
    public void enable(){
        glUseProgram(ID);
    }
    public void disable(){
        glUseProgram(0);
    }
    
    public int getUniform(String name){
        return glGetUniformLocation(ID, name);
    }
    
    public void setUniform3f(String name, Vector3f vector) {
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }
    
//    public void setUniformMat4f(String name, Matrix4f matrix) {
//        glUniformMatrix4f(getUniform(name), false, matrix.toString());
//    }
}
