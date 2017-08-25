package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeLvalueList extends SemanticAction
{
	private String actionName;

	public MakeLvalueList(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}
	
	public void execute(Stack semanticStack, Token lastToken)
	{
		LvalueList list = new LvalueList(lineNumber());
		while(true)
			if (semanticStack.peek() instanceof Lvalue)
				list.add((Lvalue)semanticStack.pop());
			else
				break;
		
		semanticStack.push(list);  
	}
	
	public String toString() { return actionName; }
}
