/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ArrayType;
import playerc.abstractsyntax.Typename;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeArrayType extends SemanticAction {
  private String actionName;

  public MakeArrayType(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Typename typename = (Typename) semanticStack.pop();
    semanticStack.push(new ArrayType(typename, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}