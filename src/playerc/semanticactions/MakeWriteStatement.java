/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ExpressionList;
import playerc.abstractsyntax.WriteStatement;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeWriteStatement extends SemanticAction {
  private String actionName;

  public MakeWriteStatement(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    // statement -> 'write' make-write-marker write-params ';'
    // make-write-statement
    ExpressionList expressions = (ExpressionList) semanticStack.pop();
    semanticStack.pop(); // pop the write-marker

    semanticStack.push(new WriteStatement(expressions, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}