/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ReturnMarker;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeReturnMarker extends SemanticAction {
  private String actionName;

  public MakeReturnMarker(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    semanticStack.push(new ReturnMarker());
  }

  public String toString() {
    return actionName;
  }
}