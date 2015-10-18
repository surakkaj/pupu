/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Main.Main;
import Main.game.Pupu;
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
public class PupuTest {

    private Main main;

    public PupuTest() {
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
//        this.main = new Main();
//        main.run();
    }

    @After
    public void tearDown() {
        main.stop();

    }

    @Test
    public void PupuCanBeCreatedTest() {

        assertNotNull(main.level.kamu);

    }

    @Test
    public void PupuCanMoveTest() {

        this.main.level.kamu.addToX(.1f);
        this.main.level.kamu.addToY(.1f);
        assertEquals(this.main.level.kamu.getPosition().x, .1f, .0f);
        assertEquals(this.main.level.kamu.getPosition().y, .1f, .0f);

    }
    

    @Test
    public void PupuCanCreateGunTest() {
        assertNotNull(this.main.level.kamu.gun);
    }

    @Test
    public void PupuCanShootTest() {
        this.main.level.kamu.gun.shoot(this.main.level.kamu.getPosition());
        this.main.level.kamu.gun.shoot(this.main.level.kamu.getPosition());
        assertTrue(this.main.level.kamu.gun.getBullets().size() == 2);
    }
    @Test
    public void PupuCanHaveCarrots(){
        this.main.level.kamu.addCarrots(1);
        assertEquals(this.main.level.kamu.getCarrots(), 1);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
