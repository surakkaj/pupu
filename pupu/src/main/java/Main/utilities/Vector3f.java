/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.utilities;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Vector3f {
    public float x, y, z;
    
    
    public Vector3f(){
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        
    }
    
    public Vector3f(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public String toString(){
        return "x:" + x + "y:" + y + "z:" + z;
    }
}
