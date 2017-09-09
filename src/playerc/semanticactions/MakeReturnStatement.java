/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.ReturnMarker;
import playerc.abstractsyntax.ReturnStatement;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeReturnStatement extends SemanticAction {
  private String actionName;

  public MakeReturnStatement(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    // at the top of the stack it's either the marker or the expression
    Expression e = null;
    if (!(semanticStack.peek() instanceof ReturnMarker))
      e = (Expression) semanticStack.pop();

    semanticStack.pop(); // pop the return-marker

    semanticStack.push(new ReturnStatement(e, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}