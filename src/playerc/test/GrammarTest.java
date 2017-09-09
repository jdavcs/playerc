/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.test;

import java.util.HashSet;
import java.util.Vector;

import junit.framework.TestCase;
import playerc.Grammar;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class GrammarTest extends TestCase {
  public void testNonterminals() {
    Grammar g = new Grammar("input/test/testGrammar1.txt", "null", "->");
    Vector v = g.nonterminals();
    assertEquals(5, v.size());
    assertTrue(v.contains("A"));
    assertTrue(v.contains("B"));
    assertTrue(v.contains("C"));
    assertTrue(v.contains("D"));
    assertTrue(v.contains("E"));
    assertFalse(v.contains("b"));
    assertFalse(v.contains("c"));
    assertFalse(v.contains("d"));
    assertFalse(v.contains("e"));
    assertFalse(v.contains("1"));
    assertFalse(v.contains("2"));
    assertFalse(v.contains("3"));
    assertFalse(v.contains("4"));
    assertFalse(v.contains("null"));
  }

  public void testTerminals() {
    Grammar g = new Grammar("input/test/testGrammar1.txt", "null", "->");
    Vector v = g.terminals();
    assertEquals(10, v.size());
    assertTrue(v.contains("b"));
    assertTrue(v.contains("c"));
    assertTrue(v.contains("d"));
    assertTrue(v.contains("e"));
    assertTrue(v.contains("1"));
    assertTrue(v.contains("2"));
    assertTrue(v.contains("3"));
    assertTrue(v.contains("4"));
    assertTrue(v.contains(Grammar.END_MARKER));
    assertFalse(v.contains("A"));
    assertFalse(v.contains("B"));
    assertFalse(v.contains("C"));
    assertFalse(v.contains("D"));
    assertFalse(v.contains("E"));
    assertTrue(v.contains("null"));
  }

  public void testGetProductions() {
    Grammar g = new Grammar("input/test/testGrammar1.txt", "null", "->");
    assertEquals(1, g.getProductions("A").size());
    assertEquals(2, g.getProductions("B").size());
    assertEquals(2, g.getProductions("C").size());
    assertEquals(1, g.getProductions("D").size());
    assertEquals(1, g.getProductions("E").size());
  }

  public void testIsNull() {
    Grammar g = new Grammar("input/test/testGrammar1.txt", "test-new-null", "->");
    assertTrue(g.isNullSymbol("test-new-null"));
    assertFalse(g.isNullSymbol("null"));
  }

  public void testIsNonterminal() {
    Grammar g = new Grammar("input/test/testGrammar1.txt", "null", "->");
    assertTrue(g.isNonterminal("A"));
    assertTrue(g.isNonterminal("B"));
    assertTrue(g.isNonterminal("C"));
    assertTrue(g.isNonterminal("D"));
    assertTrue(g.isNonterminal("E"));
    assertFalse(g.isNonterminal("b"));
    assertFalse(g.isNonterminal("c"));
    assertFalse(g.isNonterminal("d"));
    assertFalse(g.isNonterminal("e"));
    assertFalse(g.isNonterminal("1"));
    assertFalse(g.isNonterminal("2"));
    assertFalse(g.isNonterminal("3"));
    assertFalse(g.isNonterminal("4"));
    assertFalse(g.isNonterminal("null"));
  }

  public void testFirstsForGrammar1() {
    Grammar g1 = new Grammar("input/test/testGrammar1.txt", "null", "->");

    HashSet s1 = g1.getFirstSet("A");
    assertEquals(3, s1.size());
    assertTrue(s1.contains("d"));
    assertTrue(s1.contains("c"));
    assertTrue(s1.contains("b"));

    HashSet s2 = g1.getFirstSet("B");
    assertEquals(2, s2.size());
    assertTrue(s2.contains("null"));
    assertTrue(s2.contains("b"));

    HashSet s3 = g1.getFirstSet("C");
    assertEquals(2, s3.size());
    assertTrue(s3.contains("null"));
    assertTrue(s3.contains("c"));

    HashSet s4 = g1.getFirstSet("D");
    assertEquals(1, s4.size());
    assertTrue(s4.contains("d"));

    HashSet s5 = g1.getFirstSet("E");
    assertEquals(1, s5.size());
    assertTrue(s5.contains("e"));
  }

  public void testFirstsForGrammar2() {
    Grammar g2 = new Grammar("input/test/testGrammar2.txt", "null", "->");

    HashSet s1 = g2.getFirstSet("A");
    assertEquals(2, s1.size());
    assertTrue(s1.contains("c"));
    assertTrue(s1.contains("b"));

    HashSet s2 = g2.getFirstSet("B");
    assertEquals(2, s2.size());
    assertTrue(s2.contains("null"));
    assertTrue(s2.contains("b"));

    HashSet s3 = g2.getFirstSet("C");
    assertEquals(1, s3.size());
    assertTrue(s3.contains("c"));

    HashSet s4 = g2.getFirstSet("D");
    assertEquals(1, s4.size());
    assertTrue(s4.contains("d"));

  }

  public void testFirstsForGrammar3() {
    Grammar g3 = new Grammar("input/test/testGrammar3.txt", "null", "->");

    HashSet s1 = g3.getFirstSet("body");
    assertEquals(4, s1.size());
    assertTrue(s1.contains("'procedure'"));
    assertTrue(s1.contains("'type'"));
    assertTrue(s1.contains("'var'"));
    assertTrue(s1.contains("'begin'"));

    HashSet s2 = g3.getFirstSet("declarations-opt");
    assertEquals(4, s2.size());
    assertTrue(s2.contains("'var'"));
    assertTrue(s2.contains("'type'"));
    assertTrue(s2.contains("'procedure'"));
    assertTrue(s2.contains("null"));

    HashSet s3 = g3.getFirstSet("declaration");
    assertEquals(3, s3.size());
    assertTrue(s3.contains("'procedure'"));
    assertTrue(s3.contains("'type'"));
    assertTrue(s3.contains("'var'"));
  }

  public void testFirstsForGrammar4() {
    Grammar g4 = new Grammar("input/test/testGrammar4.txt", "�", ":=");

    HashSet s1 = g4.getFirstSet("expression");
    assertEquals(2, s1.size());
    assertTrue(s1.contains("("));
    assertTrue(s1.contains("identifier"));

    HashSet s2 = g4.getFirstSet("expression'");
    assertEquals(2, s2.size());
    assertTrue(s2.contains("+"));
    assertTrue(s2.contains("�"));

    HashSet s3 = g4.getFirstSet("term");
    assertEquals(2, s3.size());
    assertTrue(s3.contains("("));
    assertTrue(s3.contains("identifier"));

    HashSet s4 = g4.getFirstSet("term'");
    assertEquals(2, s4.size());
    assertTrue(s4.contains("�"));
    assertTrue(s4.contains("*"));

    HashSet s5 = g4.getFirstSet("factor");
    assertEquals(2, s5.size());
    assertTrue(s5.contains("("));
    assertTrue(s5.contains("identifier"));
  }

  public void testFirstsForGrammar5() {
    Grammar g5 = new Grammar("input/test/testGrammar5.txt", "null", "->");

    HashSet s1 = g5.getFirstSet("A");
    assertEquals(2, s1.size());
    assertTrue(s1.contains("d"));
    assertTrue(s1.contains("x"));

    HashSet s2 = g5.getFirstSet("B");
    assertEquals(2, s2.size());
    assertTrue(s2.contains("d"));
    assertTrue(s2.contains("x"));

    HashSet s3 = g5.getFirstSet("C");
    assertEquals(2, s3.size());
    assertTrue(s3.contains("d"));
    assertTrue(s3.contains("null"));

    HashSet s4 = g5.getFirstSet("D");
    assertEquals(2, s4.size());
    assertTrue(s4.contains("d"));
    assertTrue(s4.contains("null"));
  }

  public void testFirstsForGrammar6() {
    Grammar g6 = new Grammar("input/test/testGrammar6.txt", "null", "->");

    HashSet s1 = g6.getFirstSet("A");
    assertEquals(4, s1.size());
    assertTrue(s1.contains("z"));
    assertTrue(s1.contains("null"));
    assertTrue(s1.contains("y"));
    assertTrue(s1.contains("x"));

    HashSet s2 = g6.getFirstSet("B");
    assertEquals(3, s2.size());
    assertTrue(s2.contains("z"));
    assertTrue(s2.contains("y"));
    assertTrue(s2.contains("x"));

    HashSet s3 = g6.getFirstSet("C");
    assertEquals(1, s3.size());
    assertTrue(s3.contains("w"));

    HashSet s4 = g6.getFirstSet("D");
    assertEquals(2, s4.size());
    assertTrue(s4.contains("g"));
    assertTrue(s4.contains("1"));

    HashSet s5 = g6.getFirstSet("E");
    assertEquals(2, s5.size());
    assertTrue(s5.contains("null"));
    assertTrue(s5.contains("g"));

    HashSet s6 = g6.getFirstSet("F");
    assertEquals(2, s6.size());
    assertTrue(s6.contains("null"));
    assertTrue(s6.contains("g"));

    HashSet s7 = g6.getFirstSet("G");
    assertEquals(1, s7.size());
    assertTrue(s7.contains("g"));
  }

  public void testFirstsForPlayerGrammar() {
    Grammar gP = new Grammar("input/playerGrammar.txt", "�", "->");

    HashSet hs1 = gP.getFirstSet("'<'");
    assertEquals(1, hs1.size());
    assertTrue(hs1.contains("'<'"));

    HashSet hs2 = gP.getFirstSet("'>'");
    assertEquals(1, hs2.size());
    assertTrue(hs2.contains("'>'"));

    HashSet hs3 = gP.getFirstSet("lvalue-tail");
    assertEquals(3, hs3.size());
    assertTrue(hs3.contains("'.'"));
    assertTrue(hs3.contains("�"));
    assertTrue(hs3.contains("'['"));

    HashSet hs4 = gP.getFirstSet("else-opt");
    assertEquals(2, hs4.size());
    assertTrue(hs4.contains("�"));
    assertTrue(hs4.contains("'else'"));

    HashSet hs5 = gP.getFirstSet("'write'");
    assertEquals(1, hs5.size());
    assertTrue(hs5.contains("'write'"));

    HashSet hs6 = gP.getFirstSet("'-'");
    assertEquals(1, hs6.size());
    assertTrue(hs6.contains("'-'"));

    HashSet hs7 = gP.getFirstSet("expression");
    assertEquals(15, hs7.size());
    assertTrue(hs7.contains("integer"));
    assertTrue(hs7.contains("'string'"));
    assertTrue(hs7.contains("'boolean'"));
    assertTrue(hs7.contains("'real'"));
    assertTrue(hs7.contains("'-'"));
    assertTrue(hs7.contains("'true'"));
    assertTrue(hs7.contains("'false'"));
    assertTrue(hs7.contains("'('"));
    assertTrue(hs7.contains("'integer'"));
    assertTrue(hs7.contains("string"));
    assertTrue(hs7.contains("'not'"));
    assertTrue(hs7.contains("identifier"));
    assertTrue(hs7.contains("real"));
    assertTrue(hs7.contains("'+'"));
    assertTrue(hs7.contains("'null'"));

    HashSet hs8 = gP.getFirstSet("write-params-rest");
    assertEquals(16, hs8.size());
    assertTrue(hs8.contains("')'"));
    assertTrue(hs8.contains("integer"));
    assertTrue(hs8.contains("'string'"));
    assertTrue(hs8.contains("'boolean'"));
    assertTrue(hs8.contains("'real'"));
    assertTrue(hs8.contains("'-'"));
    assertTrue(hs8.contains("'true'"));
    assertTrue(hs8.contains("'false'"));
    assertTrue(hs8.contains("'('"));
    assertTrue(hs8.contains("'integer'"));
    assertTrue(hs8.contains("string"));
    assertTrue(hs8.contains("'not'"));
    assertTrue(hs8.contains("identifier"));
    assertTrue(hs8.contains("real"));
    assertTrue(hs8.contains("'null'"));
    assertTrue(hs8.contains("'+'"));

    HashSet hs9 = gP.getFirstSet("':='");
    assertEquals(1, hs9.size());
    assertTrue(hs9.contains("':='"));

    HashSet hs10 = gP.getFirstSet("'integer'");
    assertEquals(1, hs10.size());
    assertTrue(hs10.contains("'integer'"));

    HashSet hs11 = gP.getFirstSet("'>='");
    assertEquals(1, hs11.size());
    assertTrue(hs11.contains("'>='"));

    HashSet hs12 = gP.getFirstSet("string");
    assertEquals(1, hs12.size());
    assertTrue(hs12.contains("string"));

    HashSet hs13 = gP.getFirstSet("'do'");
    assertEquals(1, hs13.size());
    assertTrue(hs13.contains("'do'"));

    HashSet hs14 = gP.getFirstSet("record-init");
    assertEquals(1, hs14.size());
    assertTrue(hs14.contains("identifier"));

    HashSet hs15 = gP.getFirstSet("'<='");
    assertEquals(1, hs15.size());
    assertTrue(hs15.contains("'<='"));

    HashSet hs16 = gP.getFirstSet("'='");
    assertEquals(1, hs16.size());
    assertTrue(hs16.contains("'='"));

    HashSet hs17 = gP.getFirstSet("body");
    assertEquals(4, hs17.size());
    assertTrue(hs17.contains("'begin'"));
    assertTrue(hs17.contains("'procedure'"));
    assertTrue(hs17.contains("'type'"));
    assertTrue(hs17.contains("'var'"));

    HashSet hs18 = gP.getFirstSet("exp-then-stms");
    assertEquals(15, hs18.size());
    assertTrue(hs18.contains("integer"));
    assertTrue(hs18.contains("'string'"));
    assertTrue(hs18.contains("'boolean'"));
    assertTrue(hs18.contains("'real'"));
    assertTrue(hs18.contains("'-'"));
    assertTrue(hs18.contains("'true'"));
    assertTrue(hs18.contains("'false'"));
    assertTrue(hs18.contains("'('"));
    assertTrue(hs18.contains("'integer'"));
    assertTrue(hs18.contains("string"));
    assertTrue(hs18.contains("'not'"));
    assertTrue(hs18.contains("identifier"));
    assertTrue(hs18.contains("real"));
    assertTrue(hs18.contains("'null'"));
    assertTrue(hs18.contains("'+'"));

    HashSet hs19 = gP.getFirstSet("actual-params");
    assertEquals(1, hs19.size());
    assertTrue(hs19.contains("'('"));

    HashSet hs20 = gP.getFirstSet("array-inits-more-opt");
    assertEquals(2, hs20.size());
    assertTrue(hs20.contains("','"));
    assertTrue(hs20.contains("�"));

    HashSet hs21 = gP.getFirstSet("var-decls-opt");
    assertEquals(2, hs21.size());
    assertTrue(hs21.contains("�"));
    assertTrue(hs21.contains("identifier"));

    HashSet hs22 = gP.getFirstSet("statement-id-rest");
    assertEquals(4, hs22.size());
    assertTrue(hs22.contains("'('"));
    assertTrue(hs22.contains("':='"));
    assertTrue(hs22.contains("'.'"));
    assertTrue(hs22.contains("'['"));

    HashSet hs23 = gP.getFirstSet("formal-params-rest");
    assertEquals(2, hs23.size());
    assertTrue(hs23.contains("')'"));
    assertTrue(hs23.contains("identifier"));

    HashSet hs24 = gP.getFirstSet("'program'");
    assertEquals(1, hs24.size());
    assertTrue(hs24.contains("'program'"));

    HashSet hs25 = gP.getFirstSet("formal-params");
    assertEquals(1, hs25.size());
    assertTrue(hs25.contains("'('"));

    HashSet hs26 = gP.getFirstSet("':'");
    assertEquals(1, hs26.size());
    assertTrue(hs26.contains("':'"));

    HashSet hs27 = gP.getFirstSet("'loop'");
    assertEquals(1, hs27.size());
    assertTrue(hs27.contains("'loop'"));

    HashSet hs28 = gP.getFirstSet("'*'");
    assertEquals(1, hs28.size());
    assertTrue(hs28.contains("'*'"));

    HashSet hs29 = gP.getFirstSet("'<>'");
    assertEquals(1, hs29.size());
    assertTrue(hs29.contains("'<>'"));

    HashSet hs30 = gP.getFirstSet("write-params");
    assertEquals(1, hs30.size());
    assertTrue(hs30.contains("'('"));

    HashSet hs31 = gP.getFirstSet("lvalues-more-opt");
    assertEquals(2, hs31.size());
    assertTrue(hs31.contains("','"));
    assertTrue(hs31.contains("�"));

    HashSet hs32 = gP.getFirstSet("'read'");
    assertEquals(1, hs32.size());
    assertTrue(hs32.contains("'read'"));

    HashSet hs33 = gP.getFirstSet("expression-opt");
    assertEquals(16, hs33.size());
    assertTrue(hs33.contains("integer"));
    assertTrue(hs33.contains("'string'"));
    assertTrue(hs33.contains("'boolean'"));
    assertTrue(hs33.contains("'real'"));
    assertTrue(hs33.contains("'-'"));
    assertTrue(hs33.contains("'true'"));
    assertTrue(hs33.contains("'false'"));
    assertTrue(hs33.contains("'('"));
    assertTrue(hs33.contains("'integer'"));
    assertTrue(hs33.contains("string"));
    assertTrue(hs33.contains("'not'"));
    assertTrue(hs33.contains("�"));
    assertTrue(hs33.contains("identifier"));
    assertTrue(hs33.contains("real"));
    assertTrue(hs33.contains("'null'"));
    assertTrue(hs33.contains("'+'"));

    HashSet hs34 = gP.getFirstSet("'by'");
    assertEquals(1, hs34.size());
    assertTrue(hs34.contains("'by'"));

    HashSet hs35 = gP.getFirstSet("record-inits");
    assertEquals(1, hs35.size());
    assertTrue(hs35.contains("'{'"));

    HashSet hs36 = gP.getFirstSet("'type'");
    assertEquals(1, hs36.size());
    assertTrue(hs36.contains("'type'"));

    HashSet hs37 = gP.getFirstSet("'end'");
    assertEquals(1, hs37.size());
    assertTrue(hs37.contains("'end'"));

    HashSet hs38 = gP.getFirstSet("'real'");
    assertEquals(1, hs38.size());
    assertTrue(hs38.contains("'real'"));

    HashSet hs39 = gP.getFirstSet("member");
    assertEquals(1, hs39.size());
    assertTrue(hs39.contains("identifier"));

    HashSet hs40 = gP.getFirstSet("'true'");
    assertEquals(1, hs40.size());
    assertTrue(hs40.contains("'true'"));

    HashSet hs41 = gP.getFirstSet("'false'");
    assertEquals(1, hs41.size());
    assertTrue(hs41.contains("'false'"));

    HashSet hs42 = gP.getFirstSet("','");
    assertEquals(1, hs42.size());
    assertTrue(hs42.contains("','"));

    HashSet hs43 = gP.getFirstSet("procedure-decls-opt");
    assertEquals(2, hs43.size());
    assertTrue(hs43.contains("�"));
    assertTrue(hs43.contains("identifier"));

    HashSet hs44 = gP.getFirstSet("'elseif'");
    assertEquals(1, hs44.size());
    assertTrue(hs44.contains("'elseif'"));

    HashSet hs45 = gP.getFirstSet("'not'");
    assertEquals(1, hs45.size());
    assertTrue(hs45.contains("'not'"));

    HashSet hs46 = gP.getFirstSet("'of'");
    assertEquals(1, hs46.size());
    assertTrue(hs46.contains("'of'"));

    HashSet hs47 = gP.getFirstSet("real");
    assertEquals(1, hs47.size());
    assertTrue(hs47.contains("real"));

    HashSet hs48 = gP.getFirstSet("decl-typename-opt");
    assertEquals(2, hs48.size());
    assertTrue(hs48.contains("':'"));
    assertTrue(hs48.contains("�"));

    HashSet hs49 = gP.getFirstSet("typename");
    assertEquals(5, hs49.size());
    assertTrue(hs49.contains("'integer'"));
    assertTrue(hs49.contains("'boolean'"));
    assertTrue(hs49.contains("'string'"));
    assertTrue(hs49.contains("identifier"));
    assertTrue(hs49.contains("'real'"));

    HashSet hs50 = gP.getFirstSet("elseif");
    assertEquals(1, hs50.size());
    assertTrue(hs50.contains("'elseif'"));

    HashSet hs51 = gP.getFirstSet("$");
    assertEquals(1, hs51.size());
    assertTrue(hs51.contains("$"));

    HashSet hs52 = gP.getFirstSet("array-init");
    assertEquals(15, hs52.size());
    assertTrue(hs52.contains("integer"));
    assertTrue(hs52.contains("'string'"));
    assertTrue(hs52.contains("'boolean'"));
    assertTrue(hs52.contains("'real'"));
    assertTrue(hs52.contains("'-'"));
    assertTrue(hs52.contains("'true'"));
    assertTrue(hs52.contains("'false'"));
    assertTrue(hs52.contains("'('"));
    assertTrue(hs52.contains("'integer'"));
    assertTrue(hs52.contains("string"));
    assertTrue(hs52.contains("'not'"));
    assertTrue(hs52.contains("identifier"));
    assertTrue(hs52.contains("real"));
    assertTrue(hs52.contains("'null'"));
    assertTrue(hs52.contains("'+'"));

    HashSet hs53 = gP.getFirstSet("'return'");
    assertEquals(1, hs53.size());
    assertTrue(hs53.contains("'return'"));

    HashSet hs54 = gP.getFirstSet("')'");
    assertEquals(1, hs54.size());
    assertTrue(hs54.contains("')'"));

    HashSet hs55 = gP.getFirstSet("elseifs-opt");
    assertEquals(2, hs55.size());
    assertTrue(hs55.contains("'elseif'"));
    assertTrue(hs55.contains("�"));

    HashSet hs56 = gP.getFirstSet("members-opt");
    assertEquals(2, hs56.size());
    assertTrue(hs56.contains("�"));
    assertTrue(hs56.contains("identifier"));

    HashSet hs57 = gP.getFirstSet("'procedure'");
    assertEquals(1, hs57.size());
    assertTrue(hs57.contains("'procedure'"));

    HashSet hs58 = gP.getFirstSet("new-typename");
    assertEquals(1, hs58.size());
    assertTrue(hs58.contains("identifier"));

    HashSet hs59 = gP.getFirstSet("identifier");
    assertEquals(1, hs59.size());
    assertTrue(hs59.contains("identifier"));

    HashSet hs60 = gP.getFirstSet("declaration");
    assertEquals(3, hs60.size());
    assertTrue(hs60.contains("'procedure'"));
    assertTrue(hs60.contains("'type'"));
    assertTrue(hs60.contains("'var'"));

    HashSet hs61 = gP.getFirstSet("'null'");
    assertEquals(1, hs61.size());
    assertTrue(hs61.contains("'null'"));

    HashSet hs62 = gP.getFirstSet("'+'");
    assertEquals(1, hs62.size());
    assertTrue(hs62.contains("'+'"));

    HashSet hs63 = gP.getFirstSet("program");
    assertEquals(1, hs63.size());
    assertTrue(hs63.contains("'program'"));

    HashSet hs64 = gP.getFirstSet("lvalue");
    assertEquals(1, hs64.size());
    assertTrue(hs64.contains("identifier"));

    HashSet hs65 = gP.getFirstSet("'boolean'");
    assertEquals(1, hs65.size());
    assertTrue(hs65.contains("'boolean'"));

    HashSet hs66 = gP.getFirstSet("'else'");
    assertEquals(1, hs66.size());
    assertTrue(hs66.contains("'else'"));

    HashSet hs67 = gP.getFirstSet("identifiers-more-opt");
    assertEquals(2, hs67.size());
    assertTrue(hs67.contains("','"));
    assertTrue(hs67.contains("�"));

    HashSet hs68 = gP.getFirstSet("type-decls-opt");
    assertEquals(2, hs68.size());
    assertTrue(hs68.contains("�"));
    assertTrue(hs68.contains("identifier"));

    HashSet hs69 = gP.getFirstSet("binary-op");
    assertEquals(12, hs69.size());
    assertTrue(hs69.contains("'and'"));
    assertTrue(hs69.contains("'>='"));
    assertTrue(hs69.contains("'<'"));
    assertTrue(hs69.contains("'>'"));
    assertTrue(hs69.contains("'<>'"));
    assertTrue(hs69.contains("'*'"));
    assertTrue(hs69.contains("'<='"));
    assertTrue(hs69.contains("'='"));
    assertTrue(hs69.contains("'or'"));
    assertTrue(hs69.contains("'+'"));
    assertTrue(hs69.contains("'-'"));
    assertTrue(hs69.contains("'/'"));

    HashSet hs70 = gP.getFirstSet("statement");
    assertEquals(8, hs70.size());
    assertTrue(hs70.contains("'return'"));
    assertTrue(hs70.contains("'exit'"));
    assertTrue(hs70.contains("'loop'"));
    assertTrue(hs70.contains("'for'"));
    assertTrue(hs70.contains("identifier"));
    assertTrue(hs70.contains("'read'"));
    assertTrue(hs70.contains("'write'"));
    assertTrue(hs70.contains("'if'"));

    HashSet hs71 = gP.getFirstSet("'var'");
    assertEquals(1, hs71.size());
    assertTrue(hs71.contains("'var'"));

    HashSet hs72 = gP.getFirstSet("'>]'");
    assertEquals(1, hs72.size());
    assertTrue(hs72.contains("'>]'"));

    HashSet hs73 = gP.getFirstSet("expression-id-rest");
    assertEquals(18, hs73.size());
    assertTrue(hs73.contains("'and'"));
    assertTrue(hs73.contains("'<'"));
    assertTrue(hs73.contains("'>'"));
    assertTrue(hs73.contains("'[<'"));
    assertTrue(hs73.contains("'{'"));
    assertTrue(hs73.contains("'-'"));
    assertTrue(hs73.contains("'('"));
    assertTrue(hs73.contains("'>='"));
    assertTrue(hs73.contains("'.'"));
    assertTrue(hs73.contains("'<>'"));
    assertTrue(hs73.contains("'*'"));
    assertTrue(hs73.contains("�"));
    assertTrue(hs73.contains("'<='"));
    assertTrue(hs73.contains("'='"));
    assertTrue(hs73.contains("'or'"));
    assertTrue(hs73.contains("'['"));
    assertTrue(hs73.contains("'+'"));
    assertTrue(hs73.contains("'/'"));

    HashSet hs74 = gP.getFirstSet("integer");
    assertEquals(1, hs74.size());
    assertTrue(hs74.contains("integer"));

    HashSet hs75 = gP.getFirstSet("'to'");
    assertEquals(1, hs75.size());
    assertTrue(hs75.contains("'to'"));

    HashSet hs76 = gP.getFirstSet("'{'");
    assertEquals(1, hs76.size());
    assertTrue(hs76.contains("'{'"));

    HashSet hs77 = gP.getFirstSet("'then'");
    assertEquals(1, hs77.size());
    assertTrue(hs77.contains("'then'"));

    HashSet hs78 = gP.getFirstSet("var-decl");
    assertEquals(1, hs78.size());
    assertTrue(hs78.contains("identifier"));

    HashSet hs79 = gP.getFirstSet("array-init-tail");
    assertEquals(2, hs79.size());
    assertTrue(hs79.contains("'of'"));
    assertTrue(hs79.contains("�"));

    HashSet hs80 = gP.getFirstSet("expression-tail");
    assertEquals(13, hs80.size());
    assertTrue(hs80.contains("'and'"));
    assertTrue(hs80.contains("'<'"));
    assertTrue(hs80.contains("'>'"));
    assertTrue(hs80.contains("'-'"));
    assertTrue(hs80.contains("'>='"));
    assertTrue(hs80.contains("'<>'"));
    assertTrue(hs80.contains("'*'"));
    assertTrue(hs80.contains("�"));
    assertTrue(hs80.contains("'<='"));
    assertTrue(hs80.contains("'='"));
    assertTrue(hs80.contains("'or'"));
    assertTrue(hs80.contains("'+'"));
    assertTrue(hs80.contains("'/'"));

    HashSet hs81 = gP.getFirstSet("'for'");
    assertEquals(1, hs81.size());
    assertTrue(hs81.contains("'for'"));

    HashSet hs82 = gP.getFirstSet("'.'");
    assertEquals(1, hs82.size());
    assertTrue(hs82.contains("'.'"));

    HashSet hs83 = gP.getFirstSet("fp-section");
    assertEquals(1, hs83.size());
    assertTrue(hs83.contains("identifier"));

    HashSet hs84 = gP.getFirstSet("type-decl");
    assertEquals(1, hs84.size());
    assertTrue(hs84.contains("identifier"));

    HashSet hs85 = gP.getFirstSet("'}'");
    assertEquals(1, hs85.size());
    assertTrue(hs85.contains("'}'"));

    HashSet hs86 = gP.getFirstSet("array-inits");
    assertEquals(1, hs86.size());
    assertTrue(hs86.contains("'[<'"));

    HashSet hs87 = gP.getFirstSet("unary-op");
    assertEquals(3, hs87.size());
    assertTrue(hs87.contains("'not'"));
    assertTrue(hs87.contains("'-'"));
    assertTrue(hs87.contains("'+'"));

    HashSet hs88 = gP.getFirstSet("'string'");
    assertEquals(1, hs88.size());
    assertTrue(hs88.contains("'string'"));

    HashSet hs89 = gP.getFirstSet("actual-params-rest");
    assertEquals(16, hs89.size());
    assertTrue(hs89.contains("')'"));
    assertTrue(hs89.contains("integer"));
    assertTrue(hs89.contains("'string'"));
    assertTrue(hs89.contains("'boolean'"));
    assertTrue(hs89.contains("'real'"));
    assertTrue(hs89.contains("'-'"));
    assertTrue(hs89.contains("'true'"));
    assertTrue(hs89.contains("'false'"));
    assertTrue(hs89.contains("'('"));
    assertTrue(hs89.contains("'integer'"));
    assertTrue(hs89.contains("string"));
    assertTrue(hs89.contains("'not'"));
    assertTrue(hs89.contains("identifier"));
    assertTrue(hs89.contains("real"));
    assertTrue(hs89.contains("'null'"));
    assertTrue(hs89.contains("'+'"));

    HashSet hs90 = gP.getFirstSet("';'");
    assertEquals(1, hs90.size());
    assertTrue(hs90.contains("';'"));

    HashSet hs91 = gP.getFirstSet("']'");
    assertEquals(1, hs91.size());
    assertTrue(hs91.contains("']'"));

    HashSet hs92 = gP.getFirstSet("fp-sections-more-opt");
    assertEquals(2, hs92.size());
    assertTrue(hs92.contains("�"));
    assertTrue(hs92.contains("';'"));

    HashSet hs93 = gP.getFirstSet("prim-typename");
    assertEquals(4, hs93.size());
    assertTrue(hs93.contains("'integer'"));
    assertTrue(hs93.contains("'string'"));
    assertTrue(hs93.contains("'boolean'"));
    assertTrue(hs93.contains("'real'"));

    HashSet hs94 = gP.getFirstSet("procedure-decl");
    assertEquals(1, hs94.size());
    assertTrue(hs94.contains("identifier"));

    HashSet hs95 = gP.getFirstSet("�");
    assertEquals(1, hs95.size());
    assertTrue(hs95.contains("�"));

    HashSet hs96 = gP.getFirstSet("record-inits-more-opt");
    assertEquals(2, hs96.size());
    assertTrue(hs96.contains("�"));
    assertTrue(hs96.contains("';'"));

    HashSet hs97 = gP.getFirstSet("'['");
    assertEquals(1, hs97.size());
    assertTrue(hs97.contains("'['"));

    HashSet hs98 = gP.getFirstSet("'or'");
    assertEquals(1, hs98.size());
    assertTrue(hs98.contains("'or'"));

    HashSet hs99 = gP.getFirstSet("'/'");
    assertEquals(1, hs99.size());
    assertTrue(hs99.contains("'/'"));

    HashSet hs100 = gP.getFirstSet("statements-opt");
    assertEquals(9, hs100.size());
    assertTrue(hs100.contains("'return'"));
    assertTrue(hs100.contains("'exit'"));
    assertTrue(hs100.contains("'for'"));
    assertTrue(hs100.contains("'loop'"));
    assertTrue(hs100.contains("�"));
    assertTrue(hs100.contains("identifier"));
    assertTrue(hs100.contains("'read'"));
    assertTrue(hs100.contains("'write'"));
    assertTrue(hs100.contains("'if'"));

    HashSet hs101 = gP.getFirstSet("'and'");
    assertEquals(1, hs101.size());
    assertTrue(hs101.contains("'and'"));

    HashSet hs102 = gP.getFirstSet("'array'");
    assertEquals(1, hs102.size());
    assertTrue(hs102.contains("'array'"));

    HashSet hs103 = gP.getFirstSet("expressions-more-opt");
    assertEquals(2, hs103.size());
    assertTrue(hs103.contains("','"));
    assertTrue(hs103.contains("�"));

    HashSet hs104 = gP.getFirstSet("type");
    assertEquals(2, hs104.size());
    assertTrue(hs104.contains("'record'"));
    assertTrue(hs104.contains("'array'"));

    HashSet hs105 = gP.getFirstSet("'[<'");
    assertEquals(1, hs105.size());
    assertTrue(hs105.contains("'[<'"));

    HashSet hs106 = gP.getFirstSet("paren-expression");
    assertEquals(1, hs106.size());
    assertTrue(hs106.contains("'('"));

    HashSet hs107 = gP.getFirstSet("'if'");
    assertEquals(1, hs107.size());
    assertTrue(hs107.contains("'if'"));

    HashSet hs108 = gP.getFirstSet("'begin'");
    assertEquals(1, hs108.size());
    assertTrue(hs108.contains("'begin'"));

    HashSet hs109 = gP.getFirstSet("'record'");
    assertEquals(1, hs109.size());
    assertTrue(hs109.contains("'record'"));

    HashSet hs110 = gP.getFirstSet("'('");
    assertEquals(1, hs110.size());
    assertTrue(hs110.contains("'('"));

    HashSet hs111 = gP.getFirstSet("'exit'");
    assertEquals(1, hs111.size());
    assertTrue(hs111.contains("'exit'"));

    HashSet hs112 = gP.getFirstSet("by-expression-opt");
    assertEquals(2, hs112.size());
    assertTrue(hs112.contains("'by'"));
    assertTrue(hs112.contains("�"));

    HashSet hs113 = gP.getFirstSet("declarations-opt");
    assertEquals(4, hs113.size());
    assertTrue(hs113.contains("'procedure'"));
    assertTrue(hs113.contains("'type'"));
    assertTrue(hs113.contains("'var'"));
    assertTrue(hs113.contains("�"));

  }

  public void testFollowsForGrammar1() {
    Grammar g1 = new Grammar("input/test/testGrammar1.txt", "null", "->");

    HashSet s1 = g1.getFollowsSet("A");
    assertEquals(1, s1.size());
    assertTrue(s1.contains("$"));

    HashSet s2 = g1.getFollowsSet("B");
    assertEquals(2, s2.size());
    assertTrue(s2.contains("d"));
    assertTrue(s2.contains("c"));

    HashSet s3 = g1.getFollowsSet("C");
    assertEquals(1, s3.size());
    assertTrue(s3.contains("d"));

    HashSet s4 = g1.getFollowsSet("D");
    assertEquals(1, s4.size());
    assertTrue(s4.contains("e"));

    HashSet s5 = g1.getFollowsSet("E");
    assertEquals(1, s5.size());
    assertTrue(s5.contains("$"));
  }

  public void testFollowsForGrammar2() {
    Grammar g2 = new Grammar("input/test/testGrammar2.txt", "null", "->");

    HashSet s1 = g2.getFollowsSet("A");
    assertEquals(1, s1.size());
    assertTrue(s1.contains("$"));

    HashSet s2 = g2.getFollowsSet("B");
    assertEquals(1, s2.size());
    assertTrue(s2.contains("c"));

    HashSet s3 = g2.getFollowsSet("C");
    assertEquals(1, s3.size());
    assertTrue(s3.contains("d"));

    HashSet s4 = g2.getFollowsSet("D");
    assertEquals(1, s4.size());
    assertTrue(s4.contains("$"));
  }

  public void testFollowsForGrammar3() {
    Grammar g3 = new Grammar("input/test/testGrammar3.txt", "null", "->");

    HashSet s1 = g3.getFollowsSet("body");
    assertEquals(1, s1.size());
    assertTrue(s1.contains("$"));

    HashSet s2 = g3.getFollowsSet("declarations-opt");
    assertEquals(1, s2.size());
    assertTrue(s2.contains("'begin'"));

    HashSet s3 = g3.getFollowsSet("declaration");
    assertEquals(4, s3.size());
    assertTrue(s3.contains("'procedure'"));
    assertTrue(s3.contains("'type'"));
    assertTrue(s3.contains("'var'"));
    assertTrue(s3.contains("'begin'"));
  }

  public void testFollowsForGrammar4() {
    Grammar g4 = new Grammar("input/test/testGrammar4.txt", "�", ":=");

    HashSet s1 = g4.getFollowsSet("expression");
    assertEquals(2, s1.size());
    assertTrue(s1.contains(")"));
    assertTrue(s1.contains("$"));

    HashSet s2 = g4.getFollowsSet("factor");
    assertEquals(4, s2.size());
    assertTrue(s2.contains(")"));
    assertTrue(s2.contains("$"));
    assertTrue(s2.contains("+"));
    assertTrue(s2.contains("*"));

    HashSet s3 = g4.getFollowsSet("term'");
    assertEquals(3, s3.size());
    assertTrue(s3.contains(")"));
    assertTrue(s3.contains("$"));
    assertTrue(s3.contains("+"));

    HashSet s4 = g4.getFollowsSet("term");
    assertEquals(3, s4.size());
    assertTrue(s4.contains(")"));
    assertTrue(s4.contains("$"));
    assertTrue(s4.contains("+"));

    HashSet s5 = g4.getFollowsSet("expression'");
    assertEquals(2, s5.size());
    assertTrue(s5.contains(")"));
    assertTrue(s5.contains("$"));
  }

  public void testFollowsForGrammar5() {
    Grammar g5 = new Grammar("input/test/testGrammar5.txt", "null", "->");

    HashSet s1 = g5.getFollowsSet("A");
    assertEquals(1, s1.size());
    assertTrue(s1.contains("$"));

    HashSet s2 = g5.getFollowsSet("B");
    assertEquals(1, s2.size());
    assertTrue(s2.contains("$"));

    HashSet s3 = g5.getFollowsSet("C");
    assertEquals(1, s3.size());
    assertTrue(s3.contains("x"));

    HashSet s4 = g5.getFollowsSet("D");
    assertEquals(1, s4.size());
    assertTrue(s4.contains("x"));
  }

  public void testFollowsForGrammar6() {
    Grammar g6 = new Grammar("input/test/testGrammar6.txt", "null", "->");

    HashSet s1 = g6.getFollowsSet("A");
    assertEquals(1, s1.size());
    assertTrue(s1.contains("$"));

    HashSet s2 = g6.getFollowsSet("B");
    assertEquals(1, s2.size());
    assertTrue(s2.contains("$"));

    HashSet s3 = g6.getFollowsSet("E");
    assertEquals(1, s3.size());
    assertTrue(s3.contains("1"));

    HashSet s4 = g6.getFollowsSet("F");
    assertEquals(1, s4.size());
    assertTrue(s4.contains("1"));

    HashSet s5 = g6.getFollowsSet("G");
    assertEquals(1, s5.size());
    assertTrue(s5.contains("1"));

    HashSet s6 = g6.getFollowsSet("C");
    assertEquals(0, s6.size());

    HashSet s7 = g6.getFollowsSet("D");
    assertEquals(0, s7.size());
  }

  public void testFollowsForPlayerGrammar() {
    Grammar gP = new Grammar("input/playerGrammar.txt", "�", "->");

    HashSet hs1 = gP.getFollowsSet("lvalue");
    assertEquals(2, hs1.size());
    assertTrue(hs1.contains("','"));
    assertTrue(hs1.contains("')'"));

    HashSet hs2 = gP.getFollowsSet("lvalue-tail");
    assertEquals(24, hs2.size());
    assertTrue(hs2.contains("'and'"));
    assertTrue(hs2.contains("'>]'"));
    assertTrue(hs2.contains("'<'"));
    assertTrue(hs2.contains("'by'"));
    assertTrue(hs2.contains("'>'"));
    assertTrue(hs2.contains("')'"));
    assertTrue(hs2.contains("'to'"));
    assertTrue(hs2.contains("';'"));
    assertTrue(hs2.contains("'-'"));
    assertTrue(hs2.contains("'then'"));
    assertTrue(hs2.contains("']'"));
    assertTrue(hs2.contains("','"));
    assertTrue(hs2.contains("':='"));
    assertTrue(hs2.contains("'>='"));
    assertTrue(hs2.contains("'do'"));
    assertTrue(hs2.contains("'of'"));
    assertTrue(hs2.contains("'<>'"));
    assertTrue(hs2.contains("'*'"));
    assertTrue(hs2.contains("'<='"));
    assertTrue(hs2.contains("'='"));
    assertTrue(hs2.contains("'}'"));
    assertTrue(hs2.contains("'or'"));
    assertTrue(hs2.contains("'+'"));
    assertTrue(hs2.contains("'/'"));

    HashSet hs3 = gP.getFollowsSet("else-opt");
    assertEquals(1, hs3.size());
    assertTrue(hs3.contains("'end'"));

    HashSet hs4 = gP.getFollowsSet("expression");
    assertEquals(23, hs4.size());
    assertTrue(hs4.contains("'and'"));
    assertTrue(hs4.contains("'>]'"));
    assertTrue(hs4.contains("'<'"));
    assertTrue(hs4.contains("'by'"));
    assertTrue(hs4.contains("'>'"));
    assertTrue(hs4.contains("')'"));
    assertTrue(hs4.contains("'to'"));
    assertTrue(hs4.contains("';'"));
    assertTrue(hs4.contains("'-'"));
    assertTrue(hs4.contains("']'"));
    assertTrue(hs4.contains("'then'"));
    assertTrue(hs4.contains("','"));
    assertTrue(hs4.contains("'>='"));
    assertTrue(hs4.contains("'do'"));
    assertTrue(hs4.contains("'of'"));
    assertTrue(hs4.contains("'<>'"));
    assertTrue(hs4.contains("'*'"));
    assertTrue(hs4.contains("'<='"));
    assertTrue(hs4.contains("'='"));
    assertTrue(hs4.contains("'}'"));
    assertTrue(hs4.contains("'+'"));
    assertTrue(hs4.contains("'or'"));
    assertTrue(hs4.contains("'/'"));

    HashSet hs5 = gP.getFollowsSet("identifiers-more-opt");
    assertEquals(2, hs5.size());
    assertTrue(hs5.contains("':='"));
    assertTrue(hs5.contains("':'"));

    HashSet hs6 = gP.getFollowsSet("type-decls-opt");
    assertEquals(4, hs6.size());
    assertTrue(hs6.contains("'begin'"));
    assertTrue(hs6.contains("'procedure'"));
    assertTrue(hs6.contains("'type'"));
    assertTrue(hs6.contains("'var'"));

    HashSet hs7 = gP.getFollowsSet("write-params-rest");
    assertEquals(1, hs7.size());
    assertTrue(hs7.contains("';'"));

    HashSet hs8 = gP.getFollowsSet("binary-op");
    assertEquals(15, hs8.size());
    assertTrue(hs8.contains("integer"));
    assertTrue(hs8.contains("'string'"));
    assertTrue(hs8.contains("'boolean'"));
    assertTrue(hs8.contains("'real'"));
    assertTrue(hs8.contains("'-'"));
    assertTrue(hs8.contains("'true'"));
    assertTrue(hs8.contains("'false'"));
    assertTrue(hs8.contains("'('"));
    assertTrue(hs8.contains("'integer'"));
    assertTrue(hs8.contains("string"));
    assertTrue(hs8.contains("'not'"));
    assertTrue(hs8.contains("identifier"));
    assertTrue(hs8.contains("real"));
    assertTrue(hs8.contains("'null'"));
    assertTrue(hs8.contains("'+'"));

    HashSet hs9 = gP.getFollowsSet("statement");
    assertEquals(11, hs9.size());
    assertTrue(hs9.contains("'return'"));
    assertTrue(hs9.contains("'elseif'"));
    assertTrue(hs9.contains("'exit'"));
    assertTrue(hs9.contains("'loop'"));
    assertTrue(hs9.contains("'for'"));
    assertTrue(hs9.contains("'else'"));
    assertTrue(hs9.contains("'end'"));
    assertTrue(hs9.contains("identifier"));
    assertTrue(hs9.contains("'read'"));
    assertTrue(hs9.contains("'write'"));
    assertTrue(hs9.contains("'if'"));

    HashSet hs10 = gP.getFollowsSet("record-init");
    assertEquals(2, hs10.size());
    assertTrue(hs10.contains("'}'"));
    assertTrue(hs10.contains("';'"));

    HashSet hs11 = gP.getFollowsSet("body");
    assertEquals(2, hs11.size());
    assertTrue(hs11.contains("$"));
    assertTrue(hs11.contains("';'"));

    HashSet hs12 = gP.getFollowsSet("exp-then-stms");
    assertEquals(3, hs12.size());
    assertTrue(hs12.contains("'elseif'"));
    assertTrue(hs12.contains("'end'"));
    assertTrue(hs12.contains("'else'"));

    HashSet hs13 = gP.getFollowsSet("expression-id-rest");
    assertEquals(23, hs13.size());
    assertTrue(hs13.contains("'and'"));
    assertTrue(hs13.contains("'>]'"));
    assertTrue(hs13.contains("'<'"));
    assertTrue(hs13.contains("'by'"));
    assertTrue(hs13.contains("'>'"));
    assertTrue(hs13.contains("')'"));
    assertTrue(hs13.contains("'to'"));
    assertTrue(hs13.contains("';'"));
    assertTrue(hs13.contains("'-'"));
    assertTrue(hs13.contains("']'"));
    assertTrue(hs13.contains("'then'"));
    assertTrue(hs13.contains("','"));
    assertTrue(hs13.contains("'>='"));
    assertTrue(hs13.contains("'do'"));
    assertTrue(hs13.contains("'of'"));
    assertTrue(hs13.contains("'*'"));
    assertTrue(hs13.contains("'<>'"));
    assertTrue(hs13.contains("'<='"));
    assertTrue(hs13.contains("'='"));
    assertTrue(hs13.contains("'}'"));
    assertTrue(hs13.contains("'or'"));
    assertTrue(hs13.contains("'+'"));
    assertTrue(hs13.contains("'/'"));

    HashSet hs14 = gP.getFollowsSet("actual-params");
    assertEquals(23, hs14.size());
    assertTrue(hs14.contains("'and'"));
    assertTrue(hs14.contains("'>]'"));
    assertTrue(hs14.contains("'<'"));
    assertTrue(hs14.contains("'by'"));
    assertTrue(hs14.contains("'>'"));
    assertTrue(hs14.contains("')'"));
    assertTrue(hs14.contains("'to'"));
    assertTrue(hs14.contains("';'"));
    assertTrue(hs14.contains("'-'"));
    assertTrue(hs14.contains("'then'"));
    assertTrue(hs14.contains("']'"));
    assertTrue(hs14.contains("','"));
    assertTrue(hs14.contains("'>='"));
    assertTrue(hs14.contains("'do'"));
    assertTrue(hs14.contains("'of'"));
    assertTrue(hs14.contains("'<>'"));
    assertTrue(hs14.contains("'*'"));
    assertTrue(hs14.contains("'<='"));
    assertTrue(hs14.contains("'='"));
    assertTrue(hs14.contains("'}'"));
    assertTrue(hs14.contains("'or'"));
    assertTrue(hs14.contains("'+'"));
    assertTrue(hs14.contains("'/'"));

    HashSet hs15 = gP.getFollowsSet("array-inits-more-opt");
    assertEquals(1, hs15.size());
    assertTrue(hs15.contains("'>]'"));

    HashSet hs16 = gP.getFollowsSet("var-decls-opt");
    assertEquals(4, hs16.size());
    assertTrue(hs16.contains("'begin'"));
    assertTrue(hs16.contains("'procedure'"));
    assertTrue(hs16.contains("'type'"));
    assertTrue(hs16.contains("'var'"));

    HashSet hs17 = gP.getFollowsSet("formal-params-rest");
    assertEquals(5, hs17.size());
    assertTrue(hs17.contains("'begin'"));
    assertTrue(hs17.contains("':'"));
    assertTrue(hs17.contains("'procedure'"));
    assertTrue(hs17.contains("'type'"));
    assertTrue(hs17.contains("'var'"));

    HashSet hs18 = gP.getFollowsSet("statement-id-rest");
    assertEquals(11, hs18.size());
    assertTrue(hs18.contains("'return'"));
    assertTrue(hs18.contains("'exit'"));
    assertTrue(hs18.contains("'elseif'"));
    assertTrue(hs18.contains("'for'"));
    assertTrue(hs18.contains("'loop'"));
    assertTrue(hs18.contains("identifier"));
    assertTrue(hs18.contains("'end'"));
    assertTrue(hs18.contains("'else'"));
    assertTrue(hs18.contains("'read'"));
    assertTrue(hs18.contains("'write'"));
    assertTrue(hs18.contains("'if'"));

    HashSet hs19 = gP.getFollowsSet("var-decl");
    assertEquals(5, hs19.size());
    assertTrue(hs19.contains("'begin'"));
    assertTrue(hs19.contains("'procedure'"));
    assertTrue(hs19.contains("'type'"));
    assertTrue(hs19.contains("'var'"));
    assertTrue(hs19.contains("identifier"));

    HashSet hs20 = gP.getFollowsSet("formal-params");
    assertEquals(5, hs20.size());
    assertTrue(hs20.contains("'begin'"));
    assertTrue(hs20.contains("':'"));
    assertTrue(hs20.contains("'procedure'"));
    assertTrue(hs20.contains("'type'"));
    assertTrue(hs20.contains("'var'"));

    HashSet hs21 = gP.getFollowsSet("expression-tail");
    assertEquals(23, hs21.size());
    assertTrue(hs21.contains("'and'"));
    assertTrue(hs21.contains("'>]'"));
    assertTrue(hs21.contains("'<'"));
    assertTrue(hs21.contains("'by'"));
    assertTrue(hs21.contains("'>'"));
    assertTrue(hs21.contains("')'"));
    assertTrue(hs21.contains("'to'"));
    assertTrue(hs21.contains("';'"));
    assertTrue(hs21.contains("'-'"));
    assertTrue(hs21.contains("']'"));
    assertTrue(hs21.contains("'then'"));
    assertTrue(hs21.contains("','"));
    assertTrue(hs21.contains("'>='"));
    assertTrue(hs21.contains("'do'"));
    assertTrue(hs21.contains("'of'"));
    assertTrue(hs21.contains("'*'"));
    assertTrue(hs21.contains("'<>'"));
    assertTrue(hs21.contains("'<='"));
    assertTrue(hs21.contains("'='"));
    assertTrue(hs21.contains("'}'"));
    assertTrue(hs21.contains("'or'"));
    assertTrue(hs21.contains("'+'"));
    assertTrue(hs21.contains("'/'"));

    HashSet hs22 = gP.getFollowsSet("array-init-tail");
    assertEquals(2, hs22.size());
    assertTrue(hs22.contains("','"));
    assertTrue(hs22.contains("'>]'"));

    HashSet hs23 = gP.getFollowsSet("write-params");
    assertEquals(1, hs23.size());
    assertTrue(hs23.contains("';'"));

    HashSet hs24 = gP.getFollowsSet("fp-section");
    assertEquals(2, hs24.size());
    assertTrue(hs24.contains("')'"));
    assertTrue(hs24.contains("';'"));

    HashSet hs25 = gP.getFollowsSet("lvalues-more-opt");
    assertEquals(1, hs25.size());
    assertTrue(hs25.contains("')'"));

    HashSet hs26 = gP.getFollowsSet("array-inits");
    assertEquals(23, hs26.size());
    assertTrue(hs26.contains("'and'"));
    assertTrue(hs26.contains("'>]'"));
    assertTrue(hs26.contains("'<'"));
    assertTrue(hs26.contains("'by'"));
    assertTrue(hs26.contains("'>'"));
    assertTrue(hs26.contains("')'"));
    assertTrue(hs26.contains("'to'"));
    assertTrue(hs26.contains("';'"));
    assertTrue(hs26.contains("'-'"));
    assertTrue(hs26.contains("'then'"));
    assertTrue(hs26.contains("']'"));
    assertTrue(hs26.contains("','"));
    assertTrue(hs26.contains("'>='"));
    assertTrue(hs26.contains("'do'"));
    assertTrue(hs26.contains("'of'"));
    assertTrue(hs26.contains("'<>'"));
    assertTrue(hs26.contains("'*'"));
    assertTrue(hs26.contains("'<='"));
    assertTrue(hs26.contains("'='"));
    assertTrue(hs26.contains("'}'"));
    assertTrue(hs26.contains("'or'"));
    assertTrue(hs26.contains("'+'"));
    assertTrue(hs26.contains("'/'"));

    HashSet hs27 = gP.getFollowsSet("type-decl");
    assertEquals(5, hs27.size());
    assertTrue(hs27.contains("'begin'"));
    assertTrue(hs27.contains("'procedure'"));
    assertTrue(hs27.contains("'type'"));
    assertTrue(hs27.contains("'var'"));
    assertTrue(hs27.contains("identifier"));

    HashSet hs28 = gP.getFollowsSet("expression-opt");
    assertEquals(1, hs28.size());
    assertTrue(hs28.contains("';'"));

    HashSet hs29 = gP.getFollowsSet("record-inits");
    assertEquals(23, hs29.size());
    assertTrue(hs29.contains("'and'"));
    assertTrue(hs29.contains("'>]'"));
    assertTrue(hs29.contains("'<'"));
    assertTrue(hs29.contains("'by'"));
    assertTrue(hs29.contains("'>'"));
    assertTrue(hs29.contains("')'"));
    assertTrue(hs29.contains("'to'"));
    assertTrue(hs29.contains("';'"));
    assertTrue(hs29.contains("'-'"));
    assertTrue(hs29.contains("'then'"));
    assertTrue(hs29.contains("']'"));
    assertTrue(hs29.contains("','"));
    assertTrue(hs29.contains("'>='"));
    assertTrue(hs29.contains("'do'"));
    assertTrue(hs29.contains("'of'"));
    assertTrue(hs29.contains("'<>'"));
    assertTrue(hs29.contains("'*'"));
    assertTrue(hs29.contains("'<='"));
    assertTrue(hs29.contains("'='"));
    assertTrue(hs29.contains("'}'"));
    assertTrue(hs29.contains("'or'"));
    assertTrue(hs29.contains("'+'"));
    assertTrue(hs29.contains("'/'"));

    HashSet hs30 = gP.getFollowsSet("unary-op");
    assertEquals(15, hs30.size());
    assertTrue(hs30.contains("integer"));
    assertTrue(hs30.contains("'string'"));
    assertTrue(hs30.contains("'boolean'"));
    assertTrue(hs30.contains("'real'"));
    assertTrue(hs30.contains("'-'"));
    assertTrue(hs30.contains("'true'"));
    assertTrue(hs30.contains("'false'"));
    assertTrue(hs30.contains("'('"));
    assertTrue(hs30.contains("'integer'"));
    assertTrue(hs30.contains("string"));
    assertTrue(hs30.contains("'not'"));
    assertTrue(hs30.contains("identifier"));
    assertTrue(hs30.contains("real"));
    assertTrue(hs30.contains("'null'"));
    assertTrue(hs30.contains("'+'"));

    HashSet hs31 = gP.getFollowsSet("actual-params-rest");
    assertEquals(23, hs31.size());
    assertTrue(hs31.contains("'and'"));
    assertTrue(hs31.contains("'>]'"));
    assertTrue(hs31.contains("'<'"));
    assertTrue(hs31.contains("'by'"));
    assertTrue(hs31.contains("'>'"));
    assertTrue(hs31.contains("')'"));
    assertTrue(hs31.contains("'to'"));
    assertTrue(hs31.contains("';'"));
    assertTrue(hs31.contains("'-'"));
    assertTrue(hs31.contains("']'"));
    assertTrue(hs31.contains("'then'"));
    assertTrue(hs31.contains("','"));
    assertTrue(hs31.contains("'>='"));
    assertTrue(hs31.contains("'do'"));
    assertTrue(hs31.contains("'of'"));
    assertTrue(hs31.contains("'<>'"));
    assertTrue(hs31.contains("'*'"));
    assertTrue(hs31.contains("'<='"));
    assertTrue(hs31.contains("'='"));
    assertTrue(hs31.contains("'}'"));
    assertTrue(hs31.contains("'or'"));
    assertTrue(hs31.contains("'+'"));
    assertTrue(hs31.contains("'/'"));

    HashSet hs32 = gP.getFollowsSet("member");
    assertEquals(2, hs32.size());
    assertTrue(hs32.contains("'end'"));
    assertTrue(hs32.contains("identifier"));

    HashSet hs33 = gP.getFollowsSet("fp-sections-more-opt");
    assertEquals(1, hs33.size());
    assertTrue(hs33.contains("')'"));

    HashSet hs34 = gP.getFollowsSet("procedure-decls-opt");
    assertEquals(4, hs34.size());
    assertTrue(hs34.contains("'begin'"));
    assertTrue(hs34.contains("'procedure'"));
    assertTrue(hs34.contains("'type'"));
    assertTrue(hs34.contains("'var'"));

    HashSet hs35 = gP.getFollowsSet("prim-typename");
    assertEquals(8, hs35.size());
    assertTrue(hs35.contains("'begin'"));
    assertTrue(hs35.contains("':='"));
    assertTrue(hs35.contains("'procedure'"));
    assertTrue(hs35.contains("')'"));
    assertTrue(hs35.contains("'type'"));
    assertTrue(hs35.contains("'var'"));
    assertTrue(hs35.contains("'[<'"));
    assertTrue(hs35.contains("';'"));

    HashSet hs36 = gP.getFollowsSet("procedure-decl");
    assertEquals(5, hs36.size());
    assertTrue(hs36.contains("'begin'"));
    assertTrue(hs36.contains("'procedure'"));
    assertTrue(hs36.contains("'type'"));
    assertTrue(hs36.contains("'var'"));
    assertTrue(hs36.contains("identifier"));

    HashSet hs37 = gP.getFollowsSet("decl-typename-opt");
    assertEquals(5, hs37.size());
    assertTrue(hs37.contains("'begin'"));
    assertTrue(hs37.contains("':='"));
    assertTrue(hs37.contains("'procedure'"));
    assertTrue(hs37.contains("'type'"));
    assertTrue(hs37.contains("'var'"));

    HashSet hs38 = gP.getFollowsSet("record-inits-more-opt");
    assertEquals(1, hs38.size());
    assertTrue(hs38.contains("'}'"));

    HashSet hs39 = gP.getFollowsSet("elseif");
    assertEquals(3, hs39.size());
    assertTrue(hs39.contains("'elseif'"));
    assertTrue(hs39.contains("'else'"));
    assertTrue(hs39.contains("'end'"));

    HashSet hs40 = gP.getFollowsSet("typename");
    assertEquals(7, hs40.size());
    assertTrue(hs40.contains("'begin'"));
    assertTrue(hs40.contains("':='"));
    assertTrue(hs40.contains("')'"));
    assertTrue(hs40.contains("'procedure'"));
    assertTrue(hs40.contains("'type'"));
    assertTrue(hs40.contains("'var'"));
    assertTrue(hs40.contains("';'"));

    HashSet hs41 = gP.getFollowsSet("statements-opt");
    assertEquals(3, hs41.size());
    assertTrue(hs41.contains("'elseif'"));
    assertTrue(hs41.contains("'else'"));
    assertTrue(hs41.contains("'end'"));

    HashSet hs42 = gP.getFollowsSet("expressions-more-opt");
    assertEquals(1, hs42.size());
    assertTrue(hs42.contains("')'"));

    HashSet hs43 = gP.getFollowsSet("array-init");
    assertEquals(2, hs43.size());
    assertTrue(hs43.contains("'>]'"));
    assertTrue(hs43.contains("','"));

    HashSet hs44 = gP.getFollowsSet("elseifs-opt");
    assertEquals(2, hs44.size());
    assertTrue(hs44.contains("'end'"));
    assertTrue(hs44.contains("'else'"));

    HashSet hs45 = gP.getFollowsSet("type");
    assertEquals(1, hs45.size());
    assertTrue(hs45.contains("';'"));

    HashSet hs46 = gP.getFollowsSet("paren-expression");
    assertEquals(23, hs46.size());
    assertTrue(hs46.contains("'and'"));
    assertTrue(hs46.contains("'>]'"));
    assertTrue(hs46.contains("'<'"));
    assertTrue(hs46.contains("'by'"));
    assertTrue(hs46.contains("'>'"));
    assertTrue(hs46.contains("')'"));
    assertTrue(hs46.contains("'to'"));
    assertTrue(hs46.contains("';'"));
    assertTrue(hs46.contains("'-'"));
    assertTrue(hs46.contains("']'"));
    assertTrue(hs46.contains("'then'"));
    assertTrue(hs46.contains("','"));
    assertTrue(hs46.contains("'>='"));
    assertTrue(hs46.contains("'do'"));
    assertTrue(hs46.contains("'of'"));
    assertTrue(hs46.contains("'*'"));
    assertTrue(hs46.contains("'<>'"));
    assertTrue(hs46.contains("'<='"));
    assertTrue(hs46.contains("'='"));
    assertTrue(hs46.contains("'}'"));
    assertTrue(hs46.contains("'or'"));
    assertTrue(hs46.contains("'+'"));
    assertTrue(hs46.contains("'/'"));

    HashSet hs47 = gP.getFollowsSet("members-opt");
    assertEquals(1, hs47.size());
    assertTrue(hs47.contains("'end'"));

    HashSet hs48 = gP.getFollowsSet("by-expression-opt");
    assertEquals(1, hs48.size());
    assertTrue(hs48.contains("'do'"));

    HashSet hs49 = gP.getFollowsSet("new-typename");
    assertEquals(8, hs49.size());
    assertTrue(hs49.contains("'begin'"));
    assertTrue(hs49.contains("':='"));
    assertTrue(hs49.contains("'procedure'"));
    assertTrue(hs49.contains("')'"));
    assertTrue(hs49.contains("'type'"));
    assertTrue(hs49.contains("'var'"));
    assertTrue(hs49.contains("'='"));
    assertTrue(hs49.contains("';'"));

    HashSet hs50 = gP.getFollowsSet("declaration");
    assertEquals(4, hs50.size());
    assertTrue(hs50.contains("'begin'"));
    assertTrue(hs50.contains("'procedure'"));
    assertTrue(hs50.contains("'type'"));
    assertTrue(hs50.contains("'var'"));

    HashSet hs51 = gP.getFollowsSet("program");
    assertEquals(1, hs51.size());
    assertTrue(hs51.contains("$"));

    HashSet hs52 = gP.getFollowsSet("declarations-opt");
    assertEquals(1, hs52.size());
    assertTrue(hs52.contains("'begin'"));

  }

  public void testDerivesNull() {
    Grammar g = new Grammar("input/test/testGrammar6.txt", "null", "->");
    assertTrue(g.derivesNull("A"));
    assertFalse(g.derivesNull("B"));
    assertFalse(g.derivesNull("C"));
    assertFalse(g.derivesNull("D"));
    assertTrue(g.derivesNull("E"));
    assertTrue(g.derivesNull("F"));
    assertFalse(g.derivesNull("G"));
  }
}
