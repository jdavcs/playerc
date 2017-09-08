/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
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
  public void testToString() throws Exception {
    Vector<String> rhs = new Vector<String>();
    rhs.addElement("a");
    rhs.addElement("b");
    rhs.addElement("c");
    Production p = new Production("A", rhs);
    String expected = "A -> a b c \n";
    assertEquals(expected, p.toString());
  }
}