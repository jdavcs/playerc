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
public class MakeLvalueExpression extends SemanticAction
{
	private String actionName;

	public MakeLvalueExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}
	
	public void execute(Stack semanticStack, Token lastToken)
	{
		if (semanticStack.peek() instanceof IdLvalue)
		{
			IdLvalue lval = (IdLvalue)semanticStack.pop();
			semanticStack.push(new IdExpression(lval.id(), lineNumber()));   
		}
		else if (semanticStack.peek() instanceof DerefLvalue)
		{
			DerefLvalue lval = (DerefLvalue)semanticStack.pop();
			
			semanticStack.push(new DerefExpression(lval.lvalue(), lval.id(), lineNumber()));   
		}    
		else if (semanticStack.peek() instanceof LookupLvalue)
		{
			LookupLvalue lval = (LookupLvalue)semanticStack.pop();
			semanticStack.push(new LookupExpression(lval.lvalue(), lval.expression(), lineNumber())); 
		} 
	}
	
	public String toString() { return actionName; }
}
