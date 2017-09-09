/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.RecordInit;
import playerc.abstractsyntax.RecordInitList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeRecordInitList extends SemanticAction {
  private String actionName;

  public MakeRecordInitList(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    RecordInitList list = new RecordInitList(lineNumber());
    while (true)
      if (semanticStack.peek() instanceof RecordInit)
        list.add((RecordInit) semanticStack.pop());
      else
        break;

    semanticStack.push(list);
  }

  public String toString() {
    return actionName;
  }
}