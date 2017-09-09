/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.Declaration;
import playerc.abstractsyntax.DeclarationList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeDeclarationList extends SemanticAction {
  private String actionName;

  public MakeDeclarationList(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    DeclarationList list = new DeclarationList(lineNumber());
    while (true)
      if (semanticStack.peek() instanceof Declaration)
        list.add((Declaration) semanticStack.pop());
      else
        break;

    semanticStack.push(list);
  }

  public String toString() {
    return actionName;
  }
}