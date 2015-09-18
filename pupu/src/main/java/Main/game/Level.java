/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.game;

import Main.graphics.VertexArrayObject;
import Main.input.Keyboard;
import static org.lwjgl.glfw.GLFW.*;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class Level {

    private VertexArrayObject background;
    public Pupu kamu;

    public Level() {
        this.kamu = new Pupu();
    }

    public void render() {
        this.kamu.draw();
    }

    public void update() {
        this.kamu.update();
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

    public void pull() {
        kamu.pull();
    }

    public void pull(double mouseX, double mouseY) {
        kamu.pull(mouseX, mouseY);
    }
}
