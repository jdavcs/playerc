/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Iterator;
import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.FpSection;
import playerc.abstractsyntax.Identifier;
import playerc.abstractsyntax.IdentifierList;
import playerc.abstractsyntax.Typename;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeFpSection extends SemanticAction {
  private String actionName;

  public MakeFpSection(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    Typename typename = (Typename) semanticStack.pop();
    IdentifierList ids = (IdentifierList) semanticStack.pop();

    Iterator i = ids.iterator();
    while (i.hasNext())
      semanticStack.push(new FpSection((Identifier) i.next(), typename, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}