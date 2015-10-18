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
    public LinkedList<Villain> villainList;
    public LinkedList<GameObject> carrotList;
    private Random random;

    public Level() {
        this.kamu = new Pupu();
        this.vihu = new Villain(0.2f, "etc/bradimir.PNG", new Vector3f(0.4f, 0.4f, 1f));
        this.villainTimer = 0;
        this.villainList = new LinkedList<Villain>();
        this.carrotList = new LinkedList<GameObject>();
        this.random = new Random();
    }

    public void render() {
        this.kamu.draw();
        for (int i = 0; i < this.villainList.size(); i++) {
            this.villainList.get(i).draw();
        }
        for (int i = 0; i < this.carrotList.size(); i++) {
            this.carrotList.get(i).draw();
        }
    }

    public void update() {
        this.kamu.update();
        for (int i = 0; i < this.villainList.size(); i++) {
            this.villainList.get(i).update(kamu.getPosition());
        }
        for (int i = 0; i < this.carrotList.size(); i++) {
            this.carrotList.get(i).update();
        }
        //spawns villains every 1.5s
        if (villainTimer > 90) {
            float x = 5 * random.nextFloat() + kamu.getPosition().x - 2.5f; // only spawns to right up
            float y = 5 * random.nextFloat() + kamu.getPosition().y - 2.5f;
            float z = 5 * random.nextFloat() + kamu.getPosition().z;
            this.villainList.add(new Villain(0.2f, "etc/bradimir.PNG", new Vector3f(x, y, 2f)));
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
    public void collisionDetection() {
        for (int i = 0; i < carrotList.size(); i++) {
            if (carrotList.get(i).getPosition().x - kamu.getPosition().x < .5f
                    && carrotList.get(i).getPosition().x - kamu.getPosition().x > -.5f) {
                if (carrotList.get(i).getPosition().y - kamu.getPosition().y < .5f
                        && carrotList.get(i).getPosition().y - kamu.getPosition().y > -.5f) {
                    this.carrotList.remove(i);
                    i--;
                    kamu.addCarrots(1);

                }
            }
        }
        for (int i = 0; i < villainList.size(); i++) {
            for (int j = 0; j < villainList.get(i).gun.getBullets().size(); j++) {

                if (villainList.get(i).gun.getBullets().get(j).shouldDestroy()) {
                    villainList.get(i).gun.getBullets().remove(j);
                    j--;
                }
                if (j < 0) {
                    return;
                }

                if (villainList.get(i).gun.getBullets().get(j).getPosition().x - kamu.getPosition().x < .4f
                        && villainList.get(i).gun.getBullets().get(j).getPosition().x - kamu.getPosition().x > -.4f) {

                    if (villainList.get(i).gun.getBullets().get(j).getPosition().y - kamu.getPosition().y < .4f
                            && villainList.get(i).gun.getBullets().get(j).getPosition().y - kamu.getPosition().y > -.4f) {
                        kamu.addCarrots(-1);
                        villainList.get(i).getBullets().remove(j);
                        j--;
                    }
                }
            }

            for (int j = 0; j < kamu.gun.getBullets().size(); j++) {
                if (kamu.gun.getBullets().get(j).shouldDestroy()) {
                    kamu.gun.getBullets().remove(j);
                    j--;
                }
                if (villainList.size() == 0) {
                    return;
                }
                if (kamu.gun.getBullets().size() == 0) {
                    return;
                }
                if (j < 0 || i < 0) {
                    return;
                }
                if (j >= kamu.gun.getBullets().size()) {
                    j--;
                }
                if (i >= villainList.size()) {
                    i--;
                }

                if (kamu.gun.getBullets().get(j).getPosition().x - villainList.get(i).getPosition().x < .4f
                        && kamu.gun.getBullets().get(j).getPosition().x - villainList.get(i).getPosition().x > -.4f) {

                    if (kamu.gun.getBullets().get(j).getPosition().y - villainList.get(i).getPosition().y < .4f
                            && kamu.gun.getBullets().get(j).getPosition().y - villainList.get(i).getPosition().y > -.4f) {
                        System.out.println("hit");
                        kamu.gun.getBullets().remove(j);
                        this.carrotList.add(new Carrot(villainList.get(i).getPosition()));
                        villainList.remove(i);
                        i--;
                        j--;

                    }

                }

            }
        }
    }

}
