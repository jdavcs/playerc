/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.Identifier;
import playerc.abstractsyntax.IdentifierList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeIdList extends SemanticAction {
  private String actionName;

  public MakeIdList(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    IdentifierList list = new IdentifierList(lineNumber());

    while (true) {
      if (!semanticStack.empty() && semanticStack.peek() instanceof Identifier)
        list.add((Identifier) semanticStack.pop());
      else
        break;
    }

    semanticStack.push(list);
  }

  public String toString() {
    return actionName;
  }
}