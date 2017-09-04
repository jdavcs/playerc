package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.Identifier;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 14 2006
 * modified Sep 03 2017
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
