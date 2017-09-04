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
