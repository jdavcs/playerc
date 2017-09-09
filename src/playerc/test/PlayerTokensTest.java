/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.test;

import junit.framework.TestCase;
import playerc.PlayerTokens;
import playerc.Token;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class PlayerTokensTest extends TestCase {
  private PlayerTokens tokens;

  public void testGetTokenKeyword() {
    Token t = tokens.getWordToken("begin");
    assertEquals(PlayerTokens.KeyBegin, t.type());
    assertEquals(0, t.getIValue());
    assertTrue(t.getDValue() == 0);
    assertEquals("", t.getSValue());
  }

  public void testGetTokenId() {
    Token t = tokens.getWordToken("anduril");
    assertEquals(PlayerTokens.Identifier, t.type());
    assertEquals(0, t.getIValue());
    assertTrue(t.getDValue() == 0);
    assertEquals("anduril", t.getSValue());
  }

  protected void setUp() {
    tokens = new PlayerTokens();
  }
}