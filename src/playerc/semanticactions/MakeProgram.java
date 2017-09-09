/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.Body;
import playerc.abstractsyntax.Identifier;
import playerc.abstractsyntax.Program;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeProgram extends SemanticAction {
  private String actionName;

  public MakeProgram(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Body body = (Body) semanticStack.pop();
    Identifier id = (Identifier) semanticStack.pop();
    semanticStack.push(new Program(id, body, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}
