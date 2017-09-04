/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

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
    ExpressionList list = (ExpressionList) semanticStack.pop();
    semanticStack.pop(); // pop the marker
    Identifier id = (Identifier) semanticStack.pop();

    semanticStack.push(new ArrayInitsExpression(id, list, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}
