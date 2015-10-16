/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class VectorTest {

    Vector3f vec;

    public VectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.vec = new Vector3f();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canCreateVectorTest() {
        assertNotNull(vec);
    }

    @Test
    public void canManipulateVariablesTest() {

        vec.x = .3f;
        vec.y = .3f;
        assertTrue(vec.x == .3f);
        assertTrue(vec.y == .3f);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
