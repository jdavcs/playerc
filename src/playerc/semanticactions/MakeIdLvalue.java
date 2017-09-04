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
public class MakeIdLvalue extends SemanticAction {
  private String actionName;

  public MakeIdLvalue(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Identifier id = (Identifier) semanticStack.pop();

    semanticStack.push(new IdLvalue(id, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}
