package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeAssignmentStatement extends SemanticAction
{
	private String actionName;

	public MakeAssignmentStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		Expression exp = (Expression)semanticStack.pop();
		Lvalue lval = (Lvalue)semanticStack.pop();
		
		semanticStack.push(new AssignmentStatement(lval, exp, lineNumber()));
	}

	public String toString() { return actionName; }
}
