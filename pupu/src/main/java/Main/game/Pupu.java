/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Pupu extends GameObject {

    public Gun gun;
    private int carrots;

    public Pupu() {
        super();
        this.gun = new Gun();

    }

    public void pull() {
        this.gun.pull(this.getPosition());
    }

    @Override
    public void draw() {

        super.draw();
        this.gun.draw();
    }

    @Override
    public void update() {

        this.gun.update(this.getPosition());
    }

    void pull(double mouseX, double mouseY) {
        this.gun.pull(this.getPosition(), mouseX, mouseY);
    }

}