/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.test;

import java.util.Vector;

import junit.framework.TestCase;
import playerc.Production;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class ProductionTest extends TestCase {
  public void testToString() {
    Vector rhs = new Vector();
    rhs.addElement("a");
    rhs.addElement("b");
    rhs.addElement("c");
    Production p = new Production("A", rhs);
    String expected = "A -> a b c \n";
    assertEquals(expected, p.toString());
  }
}
