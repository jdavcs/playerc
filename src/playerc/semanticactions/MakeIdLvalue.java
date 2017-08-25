package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 9:31:06 PM
 */
public class MakeIdLvalue extends SemanticAction
{
	private String actionName;

	public MakeIdLvalue(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		Identifier id = (Identifier)semanticStack.pop();
		
		semanticStack.push(new IdLvalue(id, lineNumber()));
	}

	public String toString() { return actionName; }
}
