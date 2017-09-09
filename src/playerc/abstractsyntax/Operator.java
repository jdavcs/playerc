/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

import playerc.Token;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class Operator extends AbstractSyntaxTree {
  private Token token;

  public Operator(Token token, int lineNumber) {
    super(lineNumber);
    this.token = token;
  }

  public void accept(Visitor v) {
  }

  public Token operatorToken() {
    return token;
  }
}
