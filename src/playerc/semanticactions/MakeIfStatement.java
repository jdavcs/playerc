/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ExpThenStatementsList;
import playerc.abstractsyntax.IfStatement;
import playerc.abstractsyntax.StatementList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeIfStatement extends SemanticAction {
  private String actionName;

  public MakeIfStatement(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    // statement -> 'if' exp-then-stms elseifs-opt make-exp-then-statements-list
    // else-opt 'end' ';' make-if-statement

    StatementList elseStms = null;
    if (semanticStack.peek() instanceof StatementList)
      elseStms = (StatementList) semanticStack.pop();

    ExpThenStatementsList expStms = (ExpThenStatementsList) semanticStack.pop();

    semanticStack.push(new IfStatement(expStms, elseStms, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}