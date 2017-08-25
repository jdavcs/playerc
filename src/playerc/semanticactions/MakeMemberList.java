package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeMemberList extends SemanticAction
{
	private String actionName;

	public MakeMemberList(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      MemberList list = new MemberList(lineNumber());
	      while(true)
	         if (semanticStack.peek() instanceof Member)
	            list.add((Member)semanticStack.pop());
	         else
	            break;
	      
	      semanticStack.push(list); 
	}

	public String toString() { return actionName; }
}
