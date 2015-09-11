/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Mouse extends GLFWCursorPosCallback {
    
    private double x = 0;
    private double y = 0;
    @Override
    public void invoke(long window, double xpos, double ypos) {
       // System.out.println(xpos);
        x = xpos;
        y = ypos;
    }
    
    public  double returnX(){
        return x;
    }
    public double returnY(){
        return y;
    }
}
