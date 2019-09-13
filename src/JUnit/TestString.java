package JUnit;

import junit.framework.*;

/**
 * Un test de ejemplo sobre la clase String.
 */
public class TestString extends TestCase {
    //estos son tus test, aserciones en true
    public void testConcat1() {
        String s = "Yo soy: ";
        String s2 = s.concat("Juan Perez");
        assertTrue(s2.equals("Yo soy: Juan Perez"));
    } // testConcat1

    public void testConcat2() {
        String s = "Yo soy: ";
        String s2 = s.concat("Braian");
        assertTrue(s2.equals("Yo soy: Chute"));
    } // testConcat2

    //esto va siempre
    public static Test suite() {
        return new TestSuite(TestString.class);
    } // suite

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    } // main
} // class

