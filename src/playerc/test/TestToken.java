package playerc.test;

import playerc.PlayerTokens;
import playerc.Token;
import junit.framework.TestCase;

/**
 * Author: sergei
 * Created: Jun 18, 2005
 */
public class TestToken extends TestCase
{
   public void testInt()
   {
      Token t = new Token(PlayerTokens.IntegerNumber, 2);
      assertEquals(PlayerTokens.IntegerNumber, t.type());
      assertEquals(2, t.getIValue());
      assertTrue(t.getDValue() == 0);
      assertEquals("", t.getSValue());
   } 
   
   public void testReal()
   {
      Token t = new Token(PlayerTokens.RealNumber, 2.5);
      assertEquals(PlayerTokens.RealNumber, t.type());
      assertEquals(0, t.getIValue());
      assertTrue(t.getDValue() == 2.5);
      assertEquals("", t.getSValue());
   }    
   
   public void testString()
   {
      Token t = new Token(PlayerTokens.StringToken, "test token");
      assertEquals(PlayerTokens.StringToken, t.type());
      assertEquals(0, t.getIValue());
      assertTrue(t.getDValue() == 0);
      assertEquals("test token", t.getSValue());
   }      

   public void testOtherType()
   {
      Token t = new Token(PlayerTokens.AssignOp);
      assertEquals(PlayerTokens.AssignOp, t.type());
      assertEquals(0, t.getIValue());
      assertTrue(t.getDValue() == 0);
      assertEquals("", t.getSValue());
   }     
}