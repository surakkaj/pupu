/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.utilities.Vector3f;
import java.util.LinkedList;

/**
 * Villain is an enemy of pupu, it has its own AI
 *
 * @author Daniel Viktor Isaac
 */
public class Villain extends GameObject {

    private int timer;
    public Gun gun;
/**
 * 
 * @param size the sixe of the sprite
 * @param texturePath the location for the texture
 * @param position the startingposition of the villain
 */
    public Villain(float size, String texturePath, Vector3f position) {
        super(size, texturePath);
        this.setPosition(position);
        this.gun = new Gun();
        timer = 0;
    }
/**
 * 
 * @param pupu pupus location
 */
    public void update(Vector3f pupu) {
        this.gun.update(this.getPosition());
        if (timer > 60) {
            this.gun.shoot(this.getPosition(), (double) pupu.x * 1000, (double) -pupu.y * 1000);

            timer = 0;
        }
        timer++;
        if (this.getPosition().x < pupu.x) {
            this.addToX(.01f);
        }
        if (this.getPosition().x > pupu.x) {
            this.addToX(-.01f);
        }
        if (this.getPosition().y < pupu.y) {
            this.addToY(.01f);
        }
        if (this.getPosition().y > pupu.y) {
            this.addToY(-.01f);
        }

    }

    @Override
    public void draw() {
        super.draw();
        this.gun.draw();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LinkedList<Bullet> getBullets() {
        return this.gun.getBullets();
    }

}
