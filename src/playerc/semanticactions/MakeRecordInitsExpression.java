package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 14 2006
 * modified Sep 03 2017
 */
public class MakeRecordInitsExpression extends SemanticAction
{
	private String actionName;

	public MakeRecordInitsExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		RecordInitList list = (RecordInitList)semanticStack.pop();
		semanticStack.pop(); //pop marker
		Identifier id = (Identifier)semanticStack.pop();
		
		semanticStack.push(new RecordInitsExpression(id, list, lineNumber()));
	}

	public String toString() { return actionName; }
}
