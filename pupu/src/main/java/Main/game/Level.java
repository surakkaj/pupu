/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.graphics.VertexArrayObject;
import Main.input.Keyboard;
import Main.utilities.Vector3f;
import java.util.LinkedList;
import java.util.Random;
import static org.lwjgl.glfw.GLFW.*;

/**
 * This class is used to enclose all the gameobjects.
 *
 * @author Daniel Viktor Isaac
 */
public class Level {

    private VertexArrayObject background;

    public Pupu kamu;
    private Villain vihu;
    private int villainTimer;
    private LinkedList<Villain> villainList;
    private Random random;

    public Level() {
        this.kamu = new Pupu();
        this.vihu = new Villain(0.2f, "etc/bradimir.PNG", new Vector3f(0.4f, 0.4f, 1f));
        this.villainTimer = 0;
        this.villainList = new LinkedList<Villain>();
        this.random = new Random();
    }

    public void render() {
        this.kamu.draw();
        // this.vihu.draw();
        for (int i = 0; i < this.villainList.size(); i++) {
            this.villainList.get(i).draw();
        }
    }

    public void update() {
        this.kamu.update();
        //this.vihu.update(kamu.getPosition());
        for (int i = 0; i < this.villainList.size(); i++) {
            this.villainList.get(i).update(kamu.getPosition());
        }
        if (villainTimer > 90) {
            float x = 5 * random.nextFloat() + kamu.getPosition().x; // only spawns to right up
            float y = 5 * random.nextFloat() + kamu.getPosition().y;
            float z = 5 * random.nextFloat() + kamu.getPosition().z;
            this.villainList.add(new Villain(0.2f, "etc/bradimir.PNG", new Vector3f(x, y, 1f)));
            villainTimer = 0;
        }
        villainTimer++;
        collisionDetection();
    }

    public void checkInput() {
        if (Keyboard.isKeyDown(GLFW_KEY_A)) {
            kamu.addToX(-0.1f);
        }
        if (Keyboard.isKeyDown(GLFW_KEY_D)) {
            kamu.addToX(0.1f);
        }
        if (Keyboard.isKeyDown(GLFW_KEY_S)) {
            kamu.addToY(-0.1f);
        }
        if (Keyboard.isKeyDown(GLFW_KEY_W)) {
            kamu.addToY(0.1f);
        }
        if (Keyboard.isKeyDown(GLFW_KEY_E)) {
            kamu.addToRot(1f);
        }
    }

    public void shoot() {
        kamu.pull();
    }

    public void shoot(double mouseX, double mouseY) {
        kamu.shoot(mouseX, mouseY);

    }

    /**
     * used to check the bullet collisions by bullets from villains or pupu.
     */
    void collisionDetection() {
        for (int i = 0; i < villainList.size(); i++) {
            

            for (int j = 0; j < kamu.gun.getBullets().size(); j++) {
                if (villainList.size() == 0) {
                    return;
                }
                if (kamu.gun.getBullets().size() == 0) {
                    return;
                }
                if (j < 0 || i < 0 || j >= kamu.gun.getBullets().size() || i >= villainList.size() ) {
                    return;
                }
                
                if (kamu.gun.getBullets().get(j).getPosition().x - villainList.get(i).getPosition().x < .4f
                        && kamu.gun.getBullets().get(j).getPosition().x - villainList.get(i).getPosition().x > -.4f) {

                    if (kamu.gun.getBullets().get(j).getPosition().y - villainList.get(i).getPosition().y < .4f
                            && kamu.gun.getBullets().get(j).getPosition().y - villainList.get(i).getPosition().y > -.4f) {
                        System.out.println("hit");
                        kamu.gun.getBullets().remove(j);
                        villainList.remove(i);
                        i--;
                        j--;

                    }

                }
                
            }
        }
    }
}
