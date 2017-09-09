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
import playerc.abstractsyntax.NewTypename;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeNewTypename extends SemanticAction {
  private String actionName;

  public MakeNewTypename(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Identifier id = (Identifier) semanticStack.pop();
    semanticStack.push(new NewTypename(id, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}
