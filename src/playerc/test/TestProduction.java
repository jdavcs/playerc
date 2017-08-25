package playerc.test;

import java.util.*;

import playerc.Production;

import junit.framework.TestCase;

/**
 * Author: sergei
 * Created: Jul 15, 2005
 */
public class TestProduction extends TestCase
{
   public void testToString() throws Exception
   {
      Vector rhs = new Vector();
      rhs.addElement("a");
      rhs.addElement("b");
      rhs.addElement("c");      
      Production p = new Production("A", rhs);
      String expected = "A -> a b c \n";
      assertEquals(expected, p.toString());
   }
}
