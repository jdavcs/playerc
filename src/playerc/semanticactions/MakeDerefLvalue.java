/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.DerefLvalue;
import playerc.abstractsyntax.Identifier;
import playerc.abstractsyntax.Lvalue;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeDerefLvalue extends SemanticAction {
  private String actionName;

  public MakeDerefLvalue(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Identifier id = (Identifier) semanticStack.pop();
    Lvalue lvalue = (Lvalue) semanticStack.pop();
    semanticStack.push(new DerefLvalue(lvalue, id, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}