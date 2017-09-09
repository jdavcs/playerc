/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.CallExpression;
import playerc.abstractsyntax.ExpressionList;
import playerc.abstractsyntax.Identifier;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeCallExpression extends SemanticAction {
  private String actionName;

  public MakeCallExpression(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    ExpressionList params = null;
    if (semanticStack.peek() instanceof ExpressionList)
      params = (ExpressionList) semanticStack.pop();

    Identifier id = (Identifier) semanticStack.pop();
    semanticStack.push(new CallExpression(id, params, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}