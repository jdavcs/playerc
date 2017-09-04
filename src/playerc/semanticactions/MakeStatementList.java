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
public class MakeStatementList extends SemanticAction {
  private String actionName;

  public MakeStatementList(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    StatementList list = new StatementList(lineNumber());
    while (true)
      if (semanticStack.peek() instanceof Statement)
        list.add((Statement) semanticStack.pop());
      else
        break;

    semanticStack.push(list);
  }

  public String toString() {
    return actionName;
  }
}
