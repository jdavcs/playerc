package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeForStatement extends SemanticAction
{
	private String actionName;

	public MakeForStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		StatementList list = (StatementList)semanticStack.pop();
		
		Expression expBy = null;
		if (semanticStack.peek() instanceof Expression)
			expBy = (Expression)semanticStack.pop();
		
		semanticStack.pop(); //pop marker
		
		Expression expTo = (Expression)semanticStack.pop();
		Expression expFrom = (Expression)semanticStack.pop();
		Identifier id = (Identifier)semanticStack.pop();
		
		semanticStack.push(new ForStatement(id, expFrom, expTo, expBy, list, lineNumber()));
	}

	public String toString() { return actionName; }
}
