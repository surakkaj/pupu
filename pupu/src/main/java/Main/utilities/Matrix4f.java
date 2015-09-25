/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.utilities;

import static java.lang.Math.*;
import java.nio.FloatBuffer;

/**
 *This class is used for matrix calculations, that shaders use.
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
        /**
         * This method is used to create a new matrix, with diagonal ones.
         * 
         * @return result matrix with ones at 1.1, 2.2, 3.3, 4.4 
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
        /**
         * This method does a matrix multiplication with two matrixes
         * 
         * @param matrix used for the other matrix
         * 
         * @return the multiplied matrix of this.matrix, and the matrix given in the arguments
         */
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
        /**
         * This reads coordinates, and translates them into the correct integer array positions.
         * @param vector that has the coordinates
         * @return result is  this.matrix, but with new coordiantes.
         */
        public static Matrix4f translate(Vector3f vector){
            Matrix4f result = identity();
            
            result.matrix[0 + 3 * 4] = vector.x;
            result.matrix[1 + 3 * 4] = vector.y;
            result.matrix[2 + 3 * 4] = vector.x;
            return result;
        }
        
        /**
         * this method calculates  angles and puts them in the matrix, so any object can be rotated
         * @param angle the angle that the object is rotated. 
         * @return result is this.matrix, with new rotation coordinates.
         */
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
        
        /**
         * This method formats the matrix, so opengl knows to project it  in an ortographic view.
         * 
         * @param left the left boundaries, where the object can be rendered
         * @param right the right boundaries, where the object can be rendered
         * @param bottom the bottom boundaries, where the object can be rendered
         * @param top the top boundaries, where the object can be rendered
         * @param near the near boundaries, where the object can be rendered
         * @param far the far boundaries, where the object can be rendered
         * @return result is the new matrix calculated with the inputs
         */
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
