/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Utilities {
    
    
    
    public static FloatBuffer createFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    
    public static ByteBuffer createByteBuffer(byte[] data){
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    
    public static IntBuffer createIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    
    public static String loadAsString(String file) {
        StringBuilder result = new StringBuilder();
        try{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String buffer = "";
        while((buffer = reader.readLine()) != null){
            result.append(buffer).append("\n");
        }
        reader.close();
        }
        catch(IOException e){
            System.err.println(e.toString());
        }
        return result.toString();
    }
    
    public static int loadShader(String vertPath, String fragPath){
        String vert = loadAsString(vertPath);
        String frag = loadAsString(fragPath);
        return createShaders(vert, frag);
    }
    
    public static int createShaders(String vert, String frag){
        int program = glCreateProgram();
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);
        glCompileShader(vertID);
        glCompileShader(fragID);
        if(glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println(glGetShaderInfoLog(vertID, 2048));
        }
        if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println(glGetShaderInfoLog(fragID, 2048));
        }

        glAttachShader(program, vertID);
        glAttachShader(program, fragID);
        glLinkProgram(program);
        glValidateProgram(program);
        return program;
                
    }
    
}
