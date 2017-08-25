package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeVarDeclaration extends SemanticAction
{
	private String actionName;

	public MakeVarDeclaration(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		Expression exp = (Expression)semanticStack.pop();
		Typename tn = null;
		if (semanticStack.peek() instanceof Typename)
			tn = (Typename)semanticStack.pop();			
		Identifier id = (Identifier)semanticStack.pop();
		semanticStack.push(new VarDeclaration(id, tn, exp, lineNumber()));
	}

	public String toString() { return actionName; }
}