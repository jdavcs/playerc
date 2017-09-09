/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import playerc.abstractsyntax.Program;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class PlayerCompiler {
  public PlayerCompiler() {
  }

  public void run() {
    TableDrivenParser parser = makeParser("compiler/testParser4.txt");
    Program program = (Program) parser.parse();

    try {
      TypeChecker tc = new TypeChecker();
      tc.check(program);

      // CodeGenerator generator = new CodeGenerator(target);
      // generator.generate(program);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private TableDrivenParser makeParser(String file) {
    PlayerScanner source = null;
    try {
      source = new PlayerScanner(new BufferedReader(new FileReader(file)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Grammar gP = new Grammar("Compiler/playerGrammar.txt", "�", "->");
    return new TableDrivenParser(source, gP, new PlayerTokens());
  }

}
