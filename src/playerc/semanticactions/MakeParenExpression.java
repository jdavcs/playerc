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
public class MakeParenExpression extends SemanticAction
{
	private String actionName;

	public MakeParenExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      Expression e = (Expression)semanticStack.pop();
	      semanticStack.push(new ParenExpression(e, lineNumber()));  
	}

	public String toString() { return actionName; }
}
