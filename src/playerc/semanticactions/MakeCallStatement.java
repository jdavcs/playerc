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
public class MakeCallStatement extends SemanticAction
{
	private String actionName;

	public MakeCallStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		ExpressionList list = null;
		if (semanticStack.peek() instanceof ExpressionList)
			list = (ExpressionList)semanticStack.pop();
		
		semanticStack.pop(); //pop marker
		
		Identifier id = (Identifier)semanticStack.pop();
			
		semanticStack.push(new CallStatement(id, list, lineNumber()));			
	}

	public String toString() { return actionName; }
}
