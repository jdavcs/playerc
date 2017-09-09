/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.NewTypename;
import playerc.abstractsyntax.RecordInitList;
import playerc.abstractsyntax.RecordInitsExpression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeRecordInitsExpression extends SemanticAction {
  private String actionName;

  public MakeRecordInitsExpression(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    RecordInitList list = (RecordInitList) semanticStack.pop();
    NewTypename typename = (NewTypename) semanticStack.pop();

    semanticStack.push(new RecordInitsExpression(typename, list, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}