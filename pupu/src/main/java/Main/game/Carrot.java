/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.utilities.Vector3f;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Carrot extends GameObject{
/**
 * 
 * @param pos starting position
 */
    public Carrot(Vector3f pos) {
        super(0.3f, "etc/carrot.png");
        this.setPosition(pos);
    }

    @Override
    public void update() {
        
    }
    
}
