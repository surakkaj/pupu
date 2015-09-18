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
 *
 * @author Daniel Viktor Isaac
 */
public class Bullet extends GameObject {

    private float dx, dy, h;

    public Bullet(Vector3f pos) {

        super(0.2f);
        this.setPosition(pos);
    }

    Bullet(Vector3f pos, double mouseX, double mouseY) {
        super(0.2f);
        this.setPosition(pos);
        System.out.println("mouse:" + mouseX + " Pos:"+pos.x);
        dx = ((float)mouseX/100)  - this.getPosition().x - 6.4f;
        dy = ((float)mouseY/100) - this.getPosition().y - 3.6f;
        h = (float)Math.pow(dx, 2) + (float)Math.pow(dy, 2);
        h = (float)Math.sqrt(h);
        
    }

    @Override
    public void update() {
        //System.out.println(this.getPosition().toString());
        this.addToX(dx/h/10);
        this.addToY(-dy/h/10);
    }

}
