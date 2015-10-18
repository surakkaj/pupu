/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Main.Main;
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
public class LevelTest {
    
    private Main main;

    public LevelTest() {
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
     public void LoopTest(){
         for (int i = 0; i < 300; i++) {
             this.main.update();
         }
         assertTrue(this.main.level.villainList.size()!=0);
     }
     
//     @Test
//     public void CollisionTest(){
//         for (int i = 0; i < 100; i++) {
//             this.main.level.villainList.add(new Villain(0.2f, "etc/bradimir.PNG", (this.main.level.kamu.getPosition())));
//             this.main.level.villainList.get(i).addToX(1f);
//             
//         }
//         for (int i = 0; i < 10; i++) {
//             this.main.level.kamu.shoot(this.main.level.villainList.get(this.main.level.villainList.size()-1).getPosition().x,
//                     this.main.level.villainList.get(this.main.level.villainList.size()-1).getPosition().y);
//             this.main.level.kamu.shoot(-1, -1);
//         }
//         assertTrue(this.main.level.kamu.getCarrots()!=0);
//         
//         
//     }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
