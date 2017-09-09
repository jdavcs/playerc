/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import junit.framework.TestCase;
import playerc.Grammar;
import playerc.PlayerScanner;
import playerc.PlayerTokens;
import playerc.TableDrivenParser;
import playerc.TypeChecker;
import playerc.abstractsyntax.Program;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class TypeCheckerTest extends TestCase {
  private TypeChecker checker;

  public void testOne() {
    checker.check(makeAST("input/test/testTypeChecker1.txt"));
    assertEquals(39, checker.foundErrors());
  }

  private Program makeAST(String file) {
    PlayerScanner source = null;
    try {
      source = new PlayerScanner(new BufferedReader(new FileReader(file)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Grammar gP = new Grammar("input/playerGrammar.txt", "�", "->");
    TableDrivenParser parser = new TableDrivenParser(source, gP, new PlayerTokens());
    return (Program) parser.parse();
  }

  public void setUp() {
    checker = new TypeChecker();
  }
}