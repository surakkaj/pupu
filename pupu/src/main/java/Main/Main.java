package Main;

import Main.game.GameObject;
import Main.game.Level;
import Main.game.Pupu;
import Main.graphics.Shader;
import Main.input.Keyboard;
import Main.input.Mouse;
import Main.utilities.Matrix4f;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.opengl.GLContext;
import Main.utilities.Utilities;
import Main.utilities.Vector3f;
import static org.lwjgl.opengl.GL13.*;

/**
 * Main method to initialize opengl and used to run the program
 *
 * @author Daniel Viktor Isaac
 */
public class Main {

    public boolean running = false;
    public long window;
    public int width = 1280, height = 720;
    //Mouse is used to get callbacks for the x and y coordinates from native mouse
    public Mouse mouse;

    private boolean[] isKeyReturned = new boolean[60000];
    private GLFWKeyCallback keyCallback;
    private GLFWCursorPosCallback cursorPosCallback;

    Pupu kamu;
    public Level level;

    /**
     * used to initialize the level, input and opengl
     */
    public void init() {
        this.running = true;

        if (glfwInit() != GL_TRUE) {
            System.err.println("Init of GLFW failed");
        }

        window = glfwCreateWindow(width, height, "Pupu", NULL, NULL);

        glfwSetKeyCallback(window, keyCallback = new Keyboard());
        mouse = new Mouse();
        glfwSetCursorPosCallback(window, cursorPosCallback = mouse);

        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GLContext.createFromCurrent();

        // glClearColor(1.0f, 0.4f, 1.0f, 0.5f);
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println(glGetString(GL_VERSION));
        Shader.loadAll();

        level = new Level();

    }

    public void render() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        level.render();
        glfwSwapBuffers(window);

    }

    public void update() {
        glfwPollEvents();
        level.update();
        checkInput();

    }

    /**
     * main game loop
     */
    public void run() {
        init();

        int vao = glGenVertexArrays();
        glBindVertexArray(vao);

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        /**
         * @param running if running is set to false, the loop ends, and the program exits.
         */
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("UPS: " + updates + "FPS: " + frames);
                updates = 0;
                frames = 0;
            }

            if (glfwWindowShouldClose(window) == GL_TRUE) {
                running = false;
            }
        }

    }

    /**
     * The method used to check all inputs that relate to the main class. Also
     * contains Level.checkInput();
     */
    public void checkInput() {

        if (Keyboard.isKeyDown(GLFW_KEY_SPACE) && isKeyReturned[GLFW_KEY_SPACE]) {
            System.out.println("Space pressed");
            isKeyReturned[GLFW_KEY_SPACE] = false;
        }
        if (!Keyboard.isKeyDown(GLFW_KEY_SPACE)) {
            isKeyReturned[GLFW_KEY_SPACE] = true;
        }

        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == 1 && isKeyReturned[GLFW_MOUSE_BUTTON_1]) {
            System.out.println("x: " + mouse.returnX() + "\ny: " + mouse.returnY());
            level.shoot(mouse.returnX(), mouse.returnY());
            isKeyReturned[GLFW_MOUSE_BUTTON_1] = false;
        }
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == 0) {
            isKeyReturned[GLFW_MOUSE_BUTTON_1] = true;
        }
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_2) == 1 && isKeyReturned[GLFW_MOUSE_BUTTON_2]) {
            level.kamu.slide((float) mouse.returnX(), (float) mouse.returnY());
            isKeyReturned[GLFW_MOUSE_BUTTON_2] = false;
        }
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_2) == 0) {
            isKeyReturned[GLFW_MOUSE_BUTTON_2] = true;
        }

        level.checkInput();
        /*
         if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == 1) {
         System.out.println("x: " + mouse.returnX() + "\ny: " + mouse.returnY());
         }
         */
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("pupu");
        Main driver = new Main();
        driver.run();

    }

    public void stop() {
        this.running = false;
    }

}
