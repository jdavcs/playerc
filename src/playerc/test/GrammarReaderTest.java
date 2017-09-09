/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.test;

import java.io.IOException;
import java.util.Vector;

import junit.framework.TestCase;
import playerc.GrammarReader;
import playerc.Production;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class GrammarReaderTest extends TestCase {
  public void testGetProductions() throws IOException {
    GrammarReader r = new GrammarReader("->");
    Vector ps = r.getProductions("input/test/testGrammar1.txt", false);
    assertEquals(7, ps.size());

    Production p1 = (Production) ps.elementAt(0);
    Production p2 = (Production) ps.elementAt(1);
    Production p3 = (Production) ps.elementAt(2);
    Production p4 = (Production) ps.elementAt(3);
    Production p5 = (Production) ps.elementAt(4);
    Production p6 = (Production) ps.elementAt(5);
    Production p7 = (Production) ps.elementAt(6);

    assertEquals("A", p1.lhs());
    assertEquals("B", p2.lhs());
    assertEquals("B", p3.lhs());
    assertEquals("C", p4.lhs());
    assertEquals("C", p5.lhs());
    assertEquals("D", p6.lhs());
    assertEquals("E", p7.lhs());

    assertEquals(4, p1.rhs().size());
    assertEquals(2, p2.rhs().size());
    assertEquals(1, p3.rhs().size());
    assertEquals(2, p4.rhs().size());
    assertEquals(1, p5.rhs().size());
    assertEquals(2, p6.rhs().size());
    assertEquals(3, p7.rhs().size());

    assertEquals("B", p1.rhs().elementAt(0));
    assertEquals("C", p1.rhs().elementAt(1));
    assertEquals("D", p1.rhs().elementAt(2));
    assertEquals("E", p1.rhs().elementAt(3));
    assertEquals("b", p2.rhs().elementAt(0));
    assertEquals("1", p2.rhs().elementAt(1));
    assertEquals("null", p3.rhs().elementAt(0));
    assertEquals("c", p4.rhs().elementAt(0));
    assertEquals("2", p4.rhs().elementAt(1));
    assertEquals("null", p5.rhs().elementAt(0));
    assertEquals("d", p6.rhs().elementAt(0));
    assertEquals("3", p6.rhs().elementAt(1));
    assertEquals("e", p7.rhs().elementAt(0));
    assertEquals("4", p7.rhs().elementAt(1));
    assertEquals("3", p7.rhs().elementAt(2));
  }

  public void testHasLHS() {
    GrammarReader r = new GrammarReader("->");
    assertTrue(r.hasLHS("abc"));
    assertFalse(r.hasLHS(" abc"));
    assertFalse(r.hasLHS("|abc"));
    assertFalse(r.hasLHS("->abc"));
  }

  public void testHasRHS() {
    GrammarReader r = new GrammarReader("->");
    assertTrue(r.hasRHS("abc	-> xyz"));
    assertTrue(r.hasRHS("| xyz"));
    assertFalse(r.hasRHS("xyz"));
  }

  public void testGetLHS() {
    GrammarReader r = new GrammarReader(":=");
    assertEquals("test", r.getLHS("test := a b c"));
    assertEquals("test", r.getLHS("test		:= a b c"));
  }

  public void testMakeRHS() {
    GrammarReader r = new GrammarReader(":=");
    Vector rhs1 = r.makeRHS("abc := x1 y2 make-action z3", false);
    Vector rhs2 = r.makeRHS(" | m1 make-action n2", false);
    Vector rhs3 = r.makeRHS("abc := x1 y2 make-action z3", true);
    assertEquals(3, rhs1.size());
    assertEquals(2, rhs2.size());
    assertEquals(4, rhs3.size());
    assertEquals("x1", rhs1.elementAt(0));
    assertEquals("y2", rhs1.elementAt(1));
    assertEquals("z3", rhs1.elementAt(2));
    assertEquals("m1", rhs2.elementAt(0));
    assertEquals("n2", rhs2.elementAt(1));
    assertEquals("x1", rhs3.elementAt(0));
    assertEquals("y2", rhs3.elementAt(1));
    assertEquals("make-action", rhs3.elementAt(2));
    assertEquals("z3", rhs3.elementAt(3));
  }
}
