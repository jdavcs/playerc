/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.test;

import junit.framework.TestCase;
import playerc.Grammar;
import playerc.ParseAction;
import playerc.ParsingException;
import playerc.ParsingTable;
import playerc.PushSymbol;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class ParsingTableTest extends TestCase {
  public void testAdd() throws ParsingException {
    Grammar g = new Grammar("input/test/testGrammar1.txt", "null", "->");
    ParsingTable t = new ParsingTable(g.terminals(), g.nonterminals());
    t.add("A", "b", new PushSymbol(""));

    try {
      t.add("no such symbol", "b", new PushSymbol(""));
      fail();
    } catch (ParsingException e) {
    }
  }

  public void testLookUp() throws ParsingException {
    Grammar g = new Grammar("input/test/testGrammar1.txt", "null", "->");
    ParsingTable t = new ParsingTable(g.terminals(), g.nonterminals());
    ParseAction pa1 = new PushSymbol("");
    ParseAction pa2 = new PushSymbol("");
    t.add("A", "b", pa1);
    t.add("B", "b", pa2);

    assertTrue(pa1.equals(t.lookup("A", "b")));
    assertTrue(pa2.equals(t.lookup("B", "b")));
    assertFalse(pa1.equals(t.lookup("B", "b")));
    assertFalse(pa2.equals(t.lookup("A", "b")));

    try {
      t.lookup("no such symbol", "b");
      fail();
    } catch (ParsingException e) {
    }
  }
}
