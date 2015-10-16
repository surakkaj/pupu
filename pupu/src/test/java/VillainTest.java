/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Main.Main;
import Main.game.Pupu;
import Main.game.Villain;
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
public class VillainTest {
    
    private Main main;
    
    public VillainTest() {
        this.main = new Main();
        
        main.run();
        this.main.level.villainList.add(new Villain(0.2f, "etc/bradimir.PNG", new Vector3f(0f, 0f, 2f)));
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
    
//    @Test
//    public void VillainCanBeCreatedTest() {
//        
//        assertNotNull(main.level.villainList);
//        
//    }
    
    @Test
    public void VillainCanMoveTest() {

        //this.main.level.villainList.add(new Villain(0.2f, "etc/bradimir.PNG", new Vector3f(0f, 0f, 2f)));
        this.main.level.villainList.get(0).addToX(1f);
        assertTrue(this.main.level.villainList.get(0).getPosition().x == 1f);
        
    }
    
    @Test
    public void VillainCanCreateGunTest() {
        assertNotNull(this.main.level.villainList.get(0));
    }
    
    @Test
    public void VillainCanShootTest() {
        
        this.main.level.villainList.get(0).gun.shoot(this.main.level.kamu.getPosition());
        
        assertTrue(this.main.level.villainList.get(0).gun.getBullets().size() == 1);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
