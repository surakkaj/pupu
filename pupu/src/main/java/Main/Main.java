
package Main;

import Main.game.GameObject;
import Main.graphics.Shader;
import Main.input.Keyboard;
import Main.input.Mouse;
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
 *
 * @author Daniel Viktor Isaac
 */
public class Main {
    
    
    public boolean running = false;
    public long window;
    public int width = 1200, height = 800;
    //Mouse is used to get callbacks for the x and y coordinates from native mouse
    public Mouse mouse;
    
    private boolean[] isKeyReturned = new boolean[60000];
    private GLFWKeyCallback keyCallback;
    private GLFWCursorPosCallback cursorPosCallback;
    
    GameObject kamu;
    
    
    
    public void init(){
        this.running = true;
        
        if(glfwInit() != GL_TRUE){
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
        
        glClearColor(1.0f, 0.4f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println(glGetString(GL_VERSION));
        kamu = new GameObject(1);
        
    }
    
    public void render(){
        glfwSwapBuffers(window);
    }
    
    public void update(){
        glfwPollEvents();
        checkInput();
       // kamu.draw();
        
    }
    
    public void run(){
        init();
        
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        
        Shader shader = new Shader("src/main/java/Main/shaders/shader.vert", "src/main/java/Main/shaders/shader.vert");
        shader.enable();
        shader.setUniform3f("col", new Vector3f(0.8f, 0.8f, 0.2f));
        
        //restricting the framerate to 60
        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;       
        
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1.0){
                update();
                updates++;
                delta--;
            }            
            
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("UPS: " + updates + "FPS: " + frames);
                updates = 0;
                frames = 0;
            }
            
            
            if(glfwWindowShouldClose(window) == GL_TRUE){
                running=false;
            }
        }
        
        
    }
    
    //method for all controls
    public void checkInput() {
        
        if (Keyboard.isKeyDown(GLFW_KEY_SPACE) && isKeyReturned[GLFW_KEY_SPACE]) {
            System.out.println("Space pressed");
            isKeyReturned[GLFW_KEY_SPACE] = false;
        }
        if (!Keyboard.isKeyDown(GLFW_KEY_SPACE)){
            isKeyReturned[GLFW_KEY_SPACE] = true;
        }
        if (Keyboard.isKeyDown(GLFW_KEY_A)) {
            System.out.println("A pressed");
        }
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == 1 && isKeyReturned[GLFW_MOUSE_BUTTON_1]) {
            System.out.println("x: " + mouse.returnX() + "\ny: " + mouse.returnY());
            isKeyReturned[GLFW_MOUSE_BUTTON_1] = false;
        }
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == 0){
            isKeyReturned[GLFW_MOUSE_BUTTON_1] = true;
        }
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
    
}
