/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ExpThenStatements;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.StatementList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeExpThenStatements extends SemanticAction {
  private String actionName;

  public MakeExpThenStatements(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {

    // exp-then-stms -> expression 'then' statement statements-opt
    // make-statement-list make-exp-then-statements
    StatementList stms = (StatementList) semanticStack.pop();
    Expression exp = (Expression) semanticStack.pop();
    semanticStack.push(new ExpThenStatements(exp, stms, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}