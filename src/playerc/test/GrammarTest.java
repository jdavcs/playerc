/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.test;

import java.util.HashSet;
import java.util.Vector;

import playerc.Grammar;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class GrammarTest extends BaseTestCase {

  public void testPlayerGrammar() {
    Grammar g = new Grammar("playergrammar", "EMPTY", "->", "|");

    Vector t = g.terminals();
    //for (int i = 0; i < t.size(); i++)
      //System.out.println(t.elementAt(i));

    assertEquals(41, g.numberOfNonterminals());
    assertEquals(61, g.numberOfTerminals());

    assertTrue(g.isNonterminal("PROGRAM"));
    assertTrue(g.isNonterminal("DECLARATIONS-OPT"));
    assertTrue(g.isNonterminal("STATEMENTS-OPT"));
    assertTrue(g.isNonterminal("DECLARATION"));
    assertTrue(g.isNonterminal("VAR-DECLS-OPT"));
    assertTrue(g.isNonterminal("TYPE-DECLS-OPT"));
    assertTrue(g.isNonterminal("PROCEDURE-DECLS-OPT"));
    assertTrue(g.isNonterminal("VAR-DECL"));
    assertTrue(g.isNonterminal("TYPENAME-OPT"));
    assertTrue(g.isNonterminal("TYPE-DECL"));
    assertTrue(g.isNonterminal("TYPE-DECL-REST"));
    assertTrue(g.isNonterminal("MEMBER"));
    assertTrue(g.isNonterminal("MEMBERS-OPT"));
    assertTrue(g.isNonterminal("TYPENAME"));
    assertTrue(g.isNonterminal("PROCEDURE-DECL"));
    assertTrue(g.isNonterminal("FORMAL-PARAMS"));
    assertTrue(g.isNonterminal("FORMAL-PARAMS-REST"));
    assertTrue(g.isNonterminal("FP-SECTIONS-MORE-OPT"));
    assertTrue(g.isNonterminal("FP-SECTION"));
    assertTrue(g.isNonterminal("INNER-DECLARATIONS-OPT"));
    assertTrue(g.isNonterminal("INNER-DECLARATION"));
    assertTrue(g.isNonterminal("STATEMENT"));
    assertTrue(g.isNonterminal("STATEMENT-ID-REST"));
    assertTrue(g.isNonterminal("LVALUE"));
    assertTrue(g.isNonterminal("LVALUE-REST"));
    assertTrue(g.isNonterminal("LVALUES-MORE-OPT"));
    assertTrue(g.isNonterminal("ACTUAL-PARAMS"));
    assertTrue(g.isNonterminal("ACTUAL-PARAMS-REST"));
    assertTrue(g.isNonterminal("EXPRESSIONS-MORE-OPT"));
    assertTrue(g.isNonterminal("EXPRESSION-OPT"));
    assertTrue(g.isNonterminal("BY-EXPRESSION-OPT"));
    assertTrue(g.isNonterminal("ELSEIFS-OPT"));
    assertTrue(g.isNonterminal("ELSE-OPT"));
    assertTrue(g.isNonterminal("ELSEIF"));
    assertTrue(g.isNonterminal("BINARY-OP"));
    assertTrue(g.isNonterminal("UNARY-OP"));
    assertTrue(g.isNonterminal("EXPRESSION-REST"));
    assertTrue(g.isNonterminal("EXPRESSION"));
    assertTrue(g.isNonterminal("EXPRESSION-ID-REST"));
    assertTrue(g.isNonterminal("RECORD-INIT"));
    assertTrue(g.isNonterminal("RECORD-INITS-MORE-OPT"));

    assertFalse(g.isTerminal("PROGRAM"));
    assertFalse(g.isTerminal("DECLARATIONS-OPT"));
    assertFalse(g.isTerminal("STATEMENTS-OPT"));
    assertFalse(g.isTerminal("DECLARATION"));
    assertFalse(g.isTerminal("VAR-DECLS-OPT"));
    assertFalse(g.isTerminal("TYPE-DECLS-OPT"));
    assertFalse(g.isTerminal("PROCEDURE-DECLS-OPT"));
    assertFalse(g.isTerminal("VAR-DECL"));
    assertFalse(g.isTerminal("TYPENAME-OPT"));
    assertFalse(g.isTerminal("TYPE-DECL"));
    assertFalse(g.isTerminal("TYPE-DECL-REST"));
    assertFalse(g.isTerminal("MEMBER"));
    assertFalse(g.isTerminal("MEMBERS-OPT"));
    assertFalse(g.isTerminal("TYPENAME"));
    assertFalse(g.isTerminal("PROCEDURE-DECL"));
    assertFalse(g.isTerminal("FORMAL-PARAMS"));
    assertFalse(g.isTerminal("FORMAL-PARAMS-REST"));
    assertFalse(g.isTerminal("FP-SECTIONS-MORE-OPT"));
    assertFalse(g.isTerminal("FP-SECTION"));
    assertFalse(g.isTerminal("INNER-DECLARATIONS-OPT"));
    assertFalse(g.isTerminal("INNER-DECLARATION"));
    assertFalse(g.isTerminal("STATEMENT"));
    assertFalse(g.isTerminal("STATEMENT-ID-REST"));
    assertFalse(g.isTerminal("LVALUE"));
    assertFalse(g.isTerminal("LVALUE-REST"));
    assertFalse(g.isTerminal("LVALUES-MORE-OPT"));
    assertFalse(g.isTerminal("ACTUAL-PARAMS"));
    assertFalse(g.isTerminal("ACTUAL-PARAMS-REST"));
    assertFalse(g.isTerminal("EXPRESSIONS-MORE-OPT"));
    assertFalse(g.isTerminal("EXPRESSION-OPT"));
    assertFalse(g.isTerminal("BY-EXPRESSION-OPT"));
    assertFalse(g.isTerminal("ELSEIFS-OPT"));
    assertFalse(g.isTerminal("ELSE-OPT"));
    assertFalse(g.isTerminal("ELSEIF"));
    assertFalse(g.isTerminal("BINARY-OP"));
    assertFalse(g.isTerminal("UNARY-OP"));
    assertFalse(g.isTerminal("EXPRESSION-REST"));
    assertFalse(g.isTerminal("EXPRESSION"));
    assertFalse(g.isTerminal("EXPRESSION-ID-REST"));
    assertFalse(g.isTerminal("RECORD-INIT"));
    assertFalse(g.isTerminal("RECORD-INITS-MORE-OPT"));

    //System.out.println(g.getSemanticActions());
  }

  public void testGetStartSymbol() {
    Grammar g = new Grammar("test/source/TestGrammar1.txt", "null", "->", "|");
    assertEquals("A", g.getStartSymbol());
  }

  public void testTerminals() {
    Grammar g = new Grammar("test/source/TestGrammar1.txt", "null", "->", "|");

    assertTrue(g.isTerminal("b"));
    assertTrue(g.isTerminal("c"));
    assertTrue(g.isTerminal("d"));
    assertTrue(g.isTerminal("e"));
    assertTrue(g.isTerminal("1"));
    assertTrue(g.isTerminal("2"));
    assertTrue(g.isTerminal("3"));
    assertTrue(g.isTerminal("4"));
    assertTrue(g.isTerminal(Grammar.END_MARKER));
    assertFalse(g.isTerminal("A"));
    assertFalse(g.isTerminal("B"));
    assertFalse(g.isTerminal("C"));
    assertFalse(g.isTerminal("D"));
    assertFalse(g.isTerminal("E"));
    assertTrue(g.isTerminal("null"));
  }

  public void testGetProductions() {
    Grammar g = new Grammar("test/source/TestGrammar1.txt", "null", "->", "|");
    String method = "getProductions";
    Class[] formals = { String.class };
    String[] params1 = { "A" };
    String[] params2 = { "B" };
    String[] params3 = { "C" };
    String[] params4 = { "D" };
    String[] params5 = { "E" };

    HashSet v1 = (HashSet) invokeMethod(g, method, formals, params1);
    HashSet v2 = (HashSet) invokeMethod(g, method, formals, params2);
    HashSet v3 = (HashSet) invokeMethod(g, method, formals, params3);
    HashSet v4 = (HashSet) invokeMethod(g, method, formals, params4);
    HashSet v5 = (HashSet) invokeMethod(g, method, formals, params5);

    assertEquals(1, v1.size());
    assertEquals(2, v2.size());
    assertEquals(2, v3.size());
    assertEquals(1, v4.size());
    assertEquals(1, v5.size());
  }
}
