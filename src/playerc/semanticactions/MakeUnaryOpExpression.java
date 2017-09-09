/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.PlayerTokens;
import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.NegativeExpression;
import playerc.abstractsyntax.NotExpression;
import playerc.abstractsyntax.Operator;
import playerc.abstractsyntax.PositiveExpression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeUnaryOpExpression extends SemanticAction {
  private String actionName;

  public MakeUnaryOpExpression(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Expression e = (Expression) semanticStack.pop();
    Operator op = (Operator) semanticStack.pop();

    if (op.operatorToken().type() == PlayerTokens.MinusOp)
      semanticStack.push(new NegativeExpression(e, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.PlusOp)
      semanticStack.push(new PositiveExpression(e, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.KeyNot)
      semanticStack.push(new NotExpression(e, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}