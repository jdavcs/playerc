/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ArrayInit;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.IntegerExpression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeArrayInit extends SemanticAction {
  private String actionName;

  public MakeArrayInit(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    // array-init -> expression array-init-tail make-array-init
    // array-init-tail -> 'of' expression
    // array-init-tail -> �
    Expression exp = (Expression) semanticStack.pop();
    IntegerExpression numberOf = new IntegerExpression(1, lineNumber());

    if (semanticStack.peek() instanceof Expression)
      numberOf = (IntegerExpression) semanticStack.pop();

    semanticStack.push(new ArrayInit(numberOf, exp, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}