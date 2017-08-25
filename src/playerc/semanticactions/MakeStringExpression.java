package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeStringExpression extends SemanticAction
{
	private String actionName;

	public MakeStringExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      String value = lastToken.getSValue();
	      semanticStack.push(new StringExpression(value, lineNumber()));  
	}

	public String toString() { return actionName; }
}
