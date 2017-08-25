package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeIfStatement extends SemanticAction
{
	private String actionName;

	public MakeIfStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		ElseFragment elseFrag = null;
		if (semanticStack.peek() instanceof ElseFragment)
			elseFrag = (ElseFragment)semanticStack.pop();
		
		IfthenFragmentList ifthenFrags = (IfthenFragmentList)semanticStack.pop();
		
		semanticStack.push(new IfStatement(ifthenFrags, elseFrag, lineNumber()));
	}

	public String toString() { return actionName; }
}
