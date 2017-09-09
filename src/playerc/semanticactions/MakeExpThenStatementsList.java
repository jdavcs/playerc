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
import playerc.abstractsyntax.ExpThenStatementsList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeExpThenStatementsList extends SemanticAction {
  private String actionName;

  public MakeExpThenStatementsList(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    ExpThenStatementsList list = new ExpThenStatementsList(lineNumber());
    while (true)
      if (semanticStack.peek() instanceof ExpThenStatements)
        list.add((ExpThenStatements) semanticStack.pop());
      else
        break;

    semanticStack.push(list);
  }

  public String toString() {
    return actionName;
  }
}