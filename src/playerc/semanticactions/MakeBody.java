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
import playerc.abstractsyntax.DeclarationList;
import playerc.abstractsyntax.StatementList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeBody extends SemanticAction {
  private String actionName;

  public MakeBody(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    StatementList stms = (StatementList) semanticStack.pop();
    DeclarationList decls = (DeclarationList) semanticStack.pop();
    semanticStack.pop(); // pop the body-marker
    semanticStack.push(new Body(decls, stms, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}