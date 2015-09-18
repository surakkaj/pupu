/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.utilities;

import static java.lang.Math.*;
import java.nio.FloatBuffer;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Matrix4f {
    
        public static final int SIZE = 4 * 4;
    
        public float[] matrix = new float[SIZE];
        
        /*Making a 4x4 matrix with diagonal 1s.
        1000
        0100
        0010
        0001        
        */
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
        
        public Matrix4f multiply(Matrix4f matrix){
            Matrix4f result = new Matrix4f();
            float sum;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    sum = 0.0f;
                    for (int k = 0; k < 4; k++) {
                        sum += this.matrix[k + i * 4] * matrix.matrix[j + k * 4];
                    }
                    result.matrix[j + i * 4] = sum;
                }
            }
            return result;
        }
        
        public static Matrix4f translate(Vector3f vector){
            Matrix4f result = identity();
            
            result.matrix[0 + 3 * 4] = vector.x;
            result.matrix[1 + 3 * 4] = vector.y;
            result.matrix[2 + 3 * 4] = vector.x;
            return result;
        }
        
        //calculates the radians of the given angle, and inputs them to the matrix
        public static Matrix4f rotate(float angle){
            Matrix4f result = identity();
            float r = (float) toRadians(angle);
            float cos = (float) cos(r);
            float sin = (float) sin(r);
            
            result.matrix[0 + 0 * 4] = cos;
            result.matrix[1 + 0 * 4] = sin;
            result.matrix[0 + 1 * 4] = -sin;
            result.matrix[1 + 1 * 4] = cos;
            
            return result;
        }
        
        public static Matrix4f ortographic(float left,float right,float bottom ,float top ,float near ,float far){
            Matrix4f result = identity();
            
            result.matrix[0 + 0 * 4] = 2.0f / (right - left);
            result.matrix[1 + 1 * 4] = 2.0f / (top - bottom);
            result.matrix[2 + 2 * 4] = 2.0f / (near - far);
            
            result.matrix[0 + 3 * 4] = (left + right) / (left - right);
            result.matrix[1 + 3 * 4] = (bottom + top) / (top - bottom);
            result.matrix[2 + 3 * 4] = (near + far) / (far - near);
            
            return result;
        }
        
        public FloatBuffer toFloatBuffer(){
            return Utilities.createFloatBuffer(matrix);
        }
        
}
