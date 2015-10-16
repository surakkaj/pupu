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
    private int destructionTimer;

    public Bullet(Vector3f pos) {

        super(0.2f, "etc/bullet.png");
        this.setPosition(pos);
        this.destructionTimer = 0;
    }

    /**
     *
     * @param pos the starting point of the bullet in form of a 3 dimentional
     * vector
     * @param targetX the x location of the bullets target
     * @param targetY the y location of the bullets target
     */
    public Bullet(Vector3f pos, double targetX, double targetY) {
        super(0.2f, "etc/bullet.png");

        targetX = (targetX / 100) - 6.4; //need replacement if camera moves
        targetY = (targetY / 100) - 3.6;
        targetX *= 16 / 9;
        targetY = -targetY;
        targetY *= 1.5;
        this.setPosition(pos);

        dx = ((float) targetX) - (pos.x * 9f / 16f);
        dy = ((float) targetY) - (pos.y / 1.0f);
        h = (float) Math.pow(dx, 2) + (float) Math.pow(dy, 2);
        h = (float) Math.sqrt(h);
        this.destructionTimer = 0;

    }

    @Override
    public void update() {
        this.addToX(dx / h / 10);
        this.addToY(dy / h / 10);
        if (this.getPosition().x < -10f) {
            dx *= -1;
        }
        if (this.getPosition().x > 10f) {
            dx *= -1;
        }
        if (this.getPosition().y < -5.5f) {
            dy *= -1;
        }
        if (this.getPosition().y > 5.5f) {
            dy *= -1;
        }
        this.destructionTimer++;
    }

    public boolean shouldDestroy() {
        if (this.destructionTimer > 1000) {
            return true;
        }
        return false;
    }

}
