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
public class MakeReadStatement extends SemanticAction {
  private String actionName;

  public MakeReadStatement(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    LvalueList list = (LvalueList) semanticStack.pop();
    semanticStack.pop(); // pop marker

    semanticStack.push(new ReadStatement(list, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}
