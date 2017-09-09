/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ArrayInitList;
import playerc.abstractsyntax.ArrayInitsExpression;
import playerc.abstractsyntax.Typename;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeArrayInitsExpression extends SemanticAction {
  private String actionName;

  public MakeArrayInitsExpression(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    ArrayInitList list = (ArrayInitList) semanticStack.pop();
    Typename typename = (Typename) semanticStack.pop();

    semanticStack.push(new ArrayInitsExpression(typename, list, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}