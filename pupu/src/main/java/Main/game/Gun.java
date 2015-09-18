/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.utilities.Vector3f;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Gun extends GameObject {

    private ArrayList<Bullet> bullets;

    public Gun() {
        super(0.5f);
        this.bullets = new ArrayList<Bullet>();

        System.out.println(this.getPosition().z);

    }

    public void pull(Vector3f pos) {
        this.bullets.add(new Bullet(pos));
    }
    public void pull(Vector3f pos, double mouseX, double mouseY) {
        
        this.bullets.add(new Bullet(pos, mouseX, mouseY)); 
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

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }

    

}
