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
import playerc.abstractsyntax.AndExpression;
import playerc.abstractsyntax.DivExpression;
import playerc.abstractsyntax.EqlExpression;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.GreaterEqlExpression;
import playerc.abstractsyntax.GreaterExpression;
import playerc.abstractsyntax.LessEqlExpression;
import playerc.abstractsyntax.LessExpression;
import playerc.abstractsyntax.MinusExpression;
import playerc.abstractsyntax.MultExpression;
import playerc.abstractsyntax.NotEqlExpression;
import playerc.abstractsyntax.Operator;
import playerc.abstractsyntax.OrExpression;
import playerc.abstractsyntax.PlusExpression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeBinaryOpExpression extends SemanticAction {
  private String actionName;

  public MakeBinaryOpExpression(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Expression right = (Expression) semanticStack.pop();
    Operator op = (Operator) semanticStack.pop();
    Expression left = (Expression) semanticStack.pop();

    if (op.operatorToken().type() == PlayerTokens.MinusOp)
      semanticStack.push(new MinusExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.PlusOp)
      semanticStack.push(new PlusExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.MultOp)
      semanticStack.push(new MultExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.DivOp)
      semanticStack.push(new DivExpression(left, right, lineNumber()));

    else if (op.operatorToken().type() == PlayerTokens.KeyAnd)
      semanticStack.push(new AndExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.KeyOr)
      semanticStack.push(new OrExpression(left, right, lineNumber()));

    else if (op.operatorToken().type() == PlayerTokens.Less)
      semanticStack.push(new LessExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.LessEql)
      semanticStack.push(new LessEqlExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.Greater)
      semanticStack.push(new GreaterExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.GreaterEql)
      semanticStack.push(new GreaterEqlExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.Eql)
      semanticStack.push(new EqlExpression(left, right, lineNumber()));
    else if (op.operatorToken().type() == PlayerTokens.NotEql)
      semanticStack.push(new NotEqlExpression(left, right, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}