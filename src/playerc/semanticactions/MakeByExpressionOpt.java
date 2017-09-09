/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ByExpressionOpt;
import playerc.abstractsyntax.Expression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeByExpressionOpt extends SemanticAction {
  private String actionName;

  public MakeByExpressionOpt(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Expression e = (Expression) semanticStack.pop();
    semanticStack.push(new ByExpressionOpt(e, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}