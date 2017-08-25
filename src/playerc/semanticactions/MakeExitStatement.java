package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.ExitStatement;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeExitStatement extends SemanticAction
{
	private String actionName;

	public MakeExitStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		semanticStack.push(new ExitStatement(lineNumber()));
	}

	public String toString() { return actionName; }
}
