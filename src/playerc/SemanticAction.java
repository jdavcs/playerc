/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

import java.util.Stack;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public abstract class SemanticAction {
  private int lineNumber;

  public SemanticAction(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  protected int lineNumber() {
    return lineNumber;
  }

  public abstract void execute(Stack semanticStack, Token lastToken) throws SemanticException;

  public abstract String toString();
}