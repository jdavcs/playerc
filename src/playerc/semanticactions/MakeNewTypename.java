package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeNewTypename extends SemanticAction
{
	private String actionName;

	public MakeNewTypename(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		Identifier id = (Identifier)semanticStack.pop();
		semanticStack.push(new NewTypename(id.toString(), lineNumber()));
	}

	public String toString() { return actionName; }
}
