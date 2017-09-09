/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.LoopStatement;
import playerc.abstractsyntax.StatementList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeLoopStatement extends SemanticAction {
  private String actionName;

  public MakeLoopStatement(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    StatementList list = (StatementList) semanticStack.pop();
    semanticStack.pop(); // pop the loop-marker
    semanticStack.push(new LoopStatement(list, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}