package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeArrayInitsExpression extends SemanticAction
{
	private String actionName;

	public MakeArrayInitsExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		ExpressionList list = (ExpressionList)semanticStack.pop();
		semanticStack.pop(); //pop the marker
		Identifier id = (Identifier)semanticStack.pop();
		
		semanticStack.push(new ArrayInitsExpression(id, list, lineNumber()));
	}

	public String toString() { return actionName; }
}
