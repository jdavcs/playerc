package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeIfthenFragment extends SemanticAction
{
	private String actionName;

	public MakeIfthenFragment(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		StatementList list = (StatementList)semanticStack.pop();		
		Expression exp = (Expression)semanticStack.pop();
		
		semanticStack.push(new IfthenFragment(exp, list, lineNumber()));
	}

	public String toString() { return actionName; }
}
