package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeElseFragment extends SemanticAction
{
	private String actionName;

	public MakeElseFragment(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		StatementList list = (StatementList)semanticStack.pop();		
		semanticStack.pop(); //pop marker
		
		semanticStack.push(new ElseFragment(list, lineNumber()));
	}

	public String toString() { return actionName; }
}
