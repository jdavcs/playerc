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

  /**
   * @param args
   *          Required: path to source file; path to output directory
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    if (args.length == 2) {
      new PlayerCompiler().run(args[0], args[1]);
    } else {
      throw new Exception("required arguments missing: input file + output directory");
    }
  }

  public void run(String fileIn, String dirOut) {
    PlayerScanner source = null;
    try {
      source = new PlayerScanner(new BufferedReader(new FileReader(fileIn)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Grammar g = new Grammar("playerc/playerGrammar.txt", "�", "->");
    TableDrivenParser parser = new TableDrivenParser(source, g, new PlayerTokens());
    Program program = (Program) parser.parse();
    TypeChecker checker = new TypeChecker();
    checker.check(program);
    if (checker.foundErrors() == 0) {
      new CodeGenerator(dirOut).generate(program);
      ;
    }
  }
}