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
import playerc.abstractsyntax.FpSectionList;
import playerc.abstractsyntax.Identifier;
import playerc.abstractsyntax.ProcDeclaration;
import playerc.abstractsyntax.Typename;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeProcDeclaration extends SemanticAction {
  private String actionName;

  public MakeProcDeclaration(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {

    // procedure-decl -> identifier make-id make-formal-params-marker
    // formal-params
    // decl-typename-opt body ';' make-proc-declaration
    Body body = (Body) semanticStack.pop();

    Typename typename = null;
    if (semanticStack.peek() instanceof Typename)
      typename = (Typename) semanticStack.pop();

    FpSectionList params = null;
    if (semanticStack.peek() instanceof FpSectionList)
      params = (FpSectionList) semanticStack.pop();

    semanticStack.pop(); // pop the formal-params-marker

    Identifier id = (Identifier) semanticStack.pop();

    semanticStack.push(new ProcDeclaration(id, params, typename, body, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}