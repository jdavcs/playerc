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
public class MakeDeclarationList extends SemanticAction
{
	private String actionName;

	public MakeDeclarationList(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      DeclarationList list = new DeclarationList(lineNumber());
	      while(true)
	         if (semanticStack.peek() instanceof Declaration)
	            list.add((Declaration)semanticStack.pop());
	         else
	            break;
	      
	      semanticStack.push(list);  
	}

	public String toString() { return actionName; }
}
