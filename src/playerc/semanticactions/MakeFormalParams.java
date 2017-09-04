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
