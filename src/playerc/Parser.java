/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.io.IOException;

import playerc.abstractsyntax.AbstractSyntaxTree;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public abstract class Parser {
  private Scanner source;

  public Parser(Scanner source) {
    this.source = source;
  }

  public AbstractSyntaxTree parse() {
    AbstractSyntaxTree answer = null;
    try {
      answer = parseProgram();
    } catch (Exception e) {
      System.err.println(e);
    }
    return answer;
  }

  protected abstract AbstractSyntaxTree parseProgram()
      throws SemanticException, ParsingException, LexicalException, IOException;

  protected Scanner source() {
    return source;
  }
}
