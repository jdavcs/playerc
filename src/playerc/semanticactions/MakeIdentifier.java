package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.Identifier;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeIdentifier extends SemanticAction
{
	private String actionName;

	public MakeIdentifier(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		semanticStack.push(new Identifier(lastToken.getSValue(), lineNumber()));
	}

	public String toString() { return actionName; }
}
