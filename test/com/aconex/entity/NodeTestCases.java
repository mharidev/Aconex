/**
 * Created by meghanaharidev
 */

package com.aconex.entity;
import org.junit.*;

/**
 * Test cases for entity Node
 */
public class NodeTestCases extends Assert {


    @BeforeClass
    public static void BeforeClass() {

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testNodeCreator() {
        System.out.println("Running NodeTestCases::testNodeCreator");
        Node node = new Node(0, "@I0001@", "INDI", null);
        assertEquals(node.getLevel(), 0);
        assertEquals(node.getId(), "@I0001@");
        assertEquals(node.getTag(), "INDI");
        assertEquals(node.getValue(), null);
        assertFalse(node.isRootNode());
    }

    @Test
    public void testRootNode() {
        System.out.println("Running NodeTestCases::testRootNode");
        Node node = new Node();
        node.setLevel(-1);
        node.setId("@I0001@");
        node.setTag("INDI EXT");
        node.setValue("TEST VALUE");
        assertEquals(node.getLevel(), -1);
        assertEquals(node.getId(), "@I0001@");
        assertEquals(node.getTag(), "INDI EXT");
        assertEquals(node.getValue(), "TEST VALUE");
        assertTrue(node.isRootNode());
    }

    @After
    public void tearDown() throws Exception {

    }

    @AfterClass
    public static void AfterClass() {

    }

}
