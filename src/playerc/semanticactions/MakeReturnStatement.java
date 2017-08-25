package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeReturnStatement extends SemanticAction
{
	private String actionName;

	public MakeReturnStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		Expression exp = null;
		if (semanticStack.peek() instanceof Expression)
			exp = (Expression)semanticStack.pop();
		
		semanticStack.pop(); //pop marker
		
		semanticStack.push(new ReturnStatement(exp, lineNumber()));
	}

	public String toString() { return actionName; }
}
