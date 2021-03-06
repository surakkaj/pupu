/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;
import javax.imageio.ImageIO;
import static org.lwjgl.opengl.GL11.*;
import Main.utilities.Utilities;

/**
 * This class is used to read, and get info from textures.
 * @author Daniel Viktor Isaac
 */
public class Texture {

    private int width, height, texture;

    public Texture(String path) {
        texture = load(path);
    }
    /**
     * This method is used to translate a picture format into a format opengl can understand
     * @param path is the file path the texture is located
     * @return the texture that has been formatted so opengl can understand it
     */
    private int load(String path) {
        int[] pixels = null;

        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problem with loading the graphics " + path);
        }

        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int b = (pixels[i] & 0xff00) >> 8;
            int g = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }
        

        int tex = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, tex);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, Utilities.createIntBuffer(data));

        glBindTexture(GL_TEXTURE_2D, 0);
        return tex;
    }

    public int getWidth() {
        return width;
    }
    


    public int getHeight() {
        return height;
    }

    public int getID() {
        return texture;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
