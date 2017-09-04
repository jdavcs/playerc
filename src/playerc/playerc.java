/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

import java.io.*;

import playerc.abstractsyntax.Program;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class playerc {

  /**
   * @param args
   *          (1) input path; (2) output path (optional). Both paths can be
   *          either relative or absolute
   */
  public static void main(String[] args) {
    File input = new File(args[0]);
    String outputPath = "";
    if (args.length > 1)
      outputPath = args[1];

    PlayerScanner source = null;
    try {
      source = new PlayerScanner(new BufferedReader(new FileReader(input)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    Grammar gP = new Grammar("playerGrammar.txt", "?", "->", "|");
    TableDrivenParser parser = new TableDrivenParser(source, gP, new PlayerTokens());

    // Program program = (Program)parser.parse();
    //
    // TypeChecker checker = new TypeChecker(); checker.check(program);
    //
    // CodeGenerator generator = new CodeGenerator(outputPath); if
    // (checker.foundErrors() == 0) generator.generate(program); }

  }
}