package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeDerefLvalue extends SemanticAction
{
	private String actionName;

	public MakeDerefLvalue(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		Identifier id = (Identifier)semanticStack.pop();
		Lvalue lval = (Lvalue)semanticStack.pop();
		
		semanticStack.push(new DerefLvalue(lval, id, lineNumber()));
	}

	public String toString() { return actionName; }
}
