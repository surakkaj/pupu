/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.input.Mouse;
import Main.utilities.Vector3f;
import java.lang.Math.*;

/**
 * Class that encloses all bullet related logic.
 *
 * @author Daniel Viktor Isaac
 */
public class Bullet extends GameObject {

    private float dx, dy, h;

    public Bullet(Vector3f pos) {

        super(0.2f, "etc/ruusu.PNG");
        this.setPosition(pos);
    }
/**
 * 
 * @param pos the starting point of the bullet in form of a 3 dimentional vector
 * @param targetX the x location of the bullets target
 * @param targetY the y location of the bullets target
 */
    public Bullet(Vector3f pos, double targetX, double targetY) {
        super(0.2f, "etc/ruusu.PNG");

        targetX = (targetX / 100) - 6.4; //need replacement if camera moves
        targetY = (targetY / 100) - 3.6;
        this.setPosition(pos);
        System.out.println("mouse:" + targetX + " Pos:" + pos.x);
        System.out.println("mouse:" + targetY + " Pos:" + pos.y);
        dx = ((float) targetX) - (pos.x * 9f / 16f);
        dy = ((float) targetY) - (pos.y / 1.0f);
        h = (float) Math.pow(dx, 2) + (float) Math.pow(dy, 2);
        h = (float) Math.sqrt(h);

    }

    @Override
    public void update() {
        //System.out.println(this.getPosition().toString());
        this.addToX(dx / h / 10);
        this.addToY(-dy / h / 10);
    }

}
