package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeRealExpression extends SemanticAction
{
	private String actionName;

	public MakeRealExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      double value = lastToken.getDValue();
	      semanticStack.push(new RealExpression(value, lineNumber()));  
	}

	public String toString() { return actionName; }
}
