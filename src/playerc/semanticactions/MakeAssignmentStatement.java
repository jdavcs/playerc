/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.AssignmentStatement;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.Lvalue;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeAssignmentStatement extends SemanticAction {
  private String actionName;

  public MakeAssignmentStatement(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Expression exp = (Expression) semanticStack.pop();
    Lvalue lvalue = (Lvalue) semanticStack.pop();
    ;

    semanticStack.push(new AssignmentStatement(lvalue, exp, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}