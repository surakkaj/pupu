/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.utilities.Vector3f;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * The class that is the gun that casts bullets to the game. It sits on villains
 * and Pupu.l
 *
 * @author Daniel Viktor Isaac
 */
public class Gun extends GameObject {

    private LinkedList<Bullet> bullets;
    private int power;
/**
 * a game object with a virtual bandolier and power
 */
    public Gun() {
        super(0.5f, "etc/ruusu.PNG");
        this.bullets = new LinkedList<Bullet>();
        this.power = 1;
        System.out.println(this.getPosition().z);

    }

    public void shoot(Vector3f pos) {
        this.bullets.add(new Bullet(pos));
    }

    public void shoot(Vector3f pos, double targetX, double targetY) {

        this.bullets.add(new Bullet(pos, targetX, targetY));
    }

    public void update(Vector3f position) {
        this.setPosition(position);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }
    }

    public void draw() {
        super.draw();
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw();

        }
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LinkedList<Bullet> getBullets() {
        return this.bullets;
    }

    public void upgrade(int a) {
        this.power += a;
    }

}
