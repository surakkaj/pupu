/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Matrix4f {
    
        public static final int SIZE = 4 * 4;
    
        public float[] matrix = new float[SIZE];
        
        //Making a 4x4 matrix with diagonal ones
        public static Matrix4f identity(){
            Matrix4f result = new Matrix4f();
            for(int i = 0; i < SIZE; i++) {
                result.matrix[i] = 0.0f;
            }
            
            result.matrix[0 + 0 * 4] = 1.0f;
            result.matrix[1 + 1 * 4] = 1.0f;
            result.matrix[2 + 2 * 4] = 1.0f;
            result.matrix[3 + 3 * 4] = 1.0f;
            
            return result;
        }
        
}
