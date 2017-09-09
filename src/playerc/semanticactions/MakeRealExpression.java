/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.RealExpression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeRealExpression extends SemanticAction {
  private String actionName;

  public MakeRealExpression(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    double value = lastToken.getDValue();
    semanticStack.push(new RealExpression(value, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}