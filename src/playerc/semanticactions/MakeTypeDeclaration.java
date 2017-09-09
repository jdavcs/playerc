/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.NewType;
import playerc.abstractsyntax.NewTypename;
import playerc.abstractsyntax.TypeDeclaration;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeTypeDeclaration extends SemanticAction {
  private String actionName;

  public MakeTypeDeclaration(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    NewType type = (NewType) semanticStack.pop();
    NewTypename typename = (NewTypename) semanticStack.pop();
    semanticStack.push(new TypeDeclaration(typename, type, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}