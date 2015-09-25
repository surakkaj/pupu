/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.utilities.Vector3f;

/**
 * Villain is an enemy of pupu, it has its own AI
 * @author Daniel Viktor Isaac
 */
public class Villain extends GameObject{
       
    
    private int timer;
    private Gun gun;
    public Villain(float size, String texturePath, Vector3f position) {
        super(size, texturePath);
        this.setPosition(position);
        this.gun = new Gun();
        timer = 0;
    }

    
    public void update(Vector3f pupu) {
        this.gun.update(this.getPosition());
        if (timer > 60) {
            this.gun.shoot(this.getPosition(), (double)pupu.x*1000, (double)pupu.y*1000);
            
            
            timer = 0;
        }
        timer++;
    }
    
    @Override
    public void draw(){
        super.draw();
        this.gun.draw();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
