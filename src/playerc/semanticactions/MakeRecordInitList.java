package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeRecordInitList extends SemanticAction
{
	private String actionName;

	public MakeRecordInitList(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      RecordInitList list = new RecordInitList(lineNumber());
	      while(true)
	         if (semanticStack.peek() instanceof RecordInit)
	            list.add((RecordInit)semanticStack.pop());
	         else
	            break;
	      
	      semanticStack.push(list); 
	}

	public String toString() { return actionName; }
}
