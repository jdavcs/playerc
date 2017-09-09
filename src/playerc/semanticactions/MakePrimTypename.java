/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.PlayerTokens;
import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.PrimTypename;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakePrimTypename extends SemanticAction {
  private String actionName;

  public MakePrimTypename(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    PlayerTokens tokens = new PlayerTokens();
    String s = tokens.getTokenName(lastToken.type());
    semanticStack.push(new PrimTypename(s, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}
