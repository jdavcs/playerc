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
import playerc.abstractsyntax.ExpressionList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeExpressionList extends SemanticAction {
  private String actionName;

  public MakeExpressionList(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    ExpressionList list = new ExpressionList(lineNumber());
    while (true)
      if (semanticStack.peek() instanceof Expression)
        list.add((Expression) semanticStack.pop());
      else
        break;

    semanticStack.push(list);
  }

  public String toString() {
    return actionName;
  }
}
