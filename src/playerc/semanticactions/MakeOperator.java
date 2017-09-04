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
public class MakeOperator extends SemanticAction
{
	private String actionName;

	public MakeOperator(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		semanticStack.push(new Operator(lastToken.type(), lineNumber()));
	}

	public String toString() { return actionName; }
}
