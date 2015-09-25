/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * This class is used to get input from the keyboard.
 * @author Daniel Viktor Isaac
 */
public class Keyboard extends GLFWKeyCallback {
    
    public static boolean[] keys = new boolean[50000];

    @Override
    public void invoke(long l, int i, int i1, int i2, int i3) {
        
        keys[i] = i2 != GLFW_RELEASE;
    }
    
    public static boolean isKeyDown(int keycode){
       return keys[keycode];
    }
     
}
