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
public class MakeExpressionList extends SemanticAction
{
	private String actionName;

	public MakeExpressionList(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      ExpressionList list = new ExpressionList(lineNumber());
	      while(true)
	         if (semanticStack.peek() instanceof Expression)
	            list.add((Expression)semanticStack.pop());
	         else
	            break;
	      
	      semanticStack.push(list);  
	}

	public String toString() { return actionName; }
}
