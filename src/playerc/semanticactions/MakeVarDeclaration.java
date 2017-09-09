/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.IdentifierList;
import playerc.abstractsyntax.Typename;
import playerc.abstractsyntax.VarDeclaration;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeVarDeclaration extends SemanticAction {
  private String actionName;

  public MakeVarDeclaration(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    // var-decl -> identifier make-id identifiers-more-opt make-id-list
    // decl-typename-opt ':=' expression ';' make-var-declaration
    Expression exp = (Expression) semanticStack.pop();

    Typename typename = null;
    if (semanticStack.peek() instanceof Typename)
      typename = (Typename) semanticStack.pop();

    IdentifierList ids = (IdentifierList) semanticStack.pop();

    semanticStack.push(new VarDeclaration(ids, typename, exp, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}