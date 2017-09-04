package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.ExitStatement;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 14 2006
 * modified Sep 03 2017
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
