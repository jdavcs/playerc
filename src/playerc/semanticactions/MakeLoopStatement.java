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
public class MakeLoopStatement extends SemanticAction
{
	private String actionName;

	public MakeLoopStatement(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		StatementList list = (StatementList)semanticStack.pop();
		semanticStack.pop(); //pop marker
		
		semanticStack.push(new LoopStatement(list, lineNumber()));
	}

	public String toString() { return actionName; }
}
