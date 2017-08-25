package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeFormalParams extends SemanticAction
{
	private String actionName;

	public MakeFormalParams(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		FPSectionList list = null;
		if (semanticStack.peek() instanceof FPSectionList)
			list = (FPSectionList)semanticStack.pop();
		
		semanticStack.push(new FormalParams(list, lineNumber()));
	}

	public String toString() { return actionName; }
}
