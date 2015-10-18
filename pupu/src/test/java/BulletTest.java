/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Main.Main;
import Main.game.Bullet;
import Main.utilities.Vector3f;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Viktor Isaac
 */
public class BulletTest {

    private Main main;

    public BulletTest() {
        this.main = new Main();
        main.run();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        main.stop();
    }

    @Test
    public void BulletCanBeCreatedTest() {
        Bullet bullet = new Bullet(new Vector3f(0f, 0f, 0f), 0.1, 0.1);
        bullet.update();
        assertNotNull(bullet);
    }

    @Test
    public void BulletCanMove() {
        Bullet bullet = new Bullet(new Vector3f(0f, 0f, 0f), 0.1, 0.1);
        bullet.update();
        assertTrue(bullet.getPosition().x != 0f);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
