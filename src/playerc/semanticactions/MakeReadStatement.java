package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeReadStatement extends SemanticAction
{
	private String actionName;

	public MakeReadStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		LvalueList list = (LvalueList)semanticStack.pop();
		semanticStack.pop(); //pop marker
		
		semanticStack.push(new ReadStatement(list, lineNumber()));		
	}

	public String toString() { return actionName; }
}
