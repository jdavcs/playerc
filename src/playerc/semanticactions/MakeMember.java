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
public class MakeMember extends SemanticAction
{
	private String actionName;

	public MakeMember(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		//member -> IDENTIFIER MAKE-id ':' typename ';' MAKE-member
		Typename tn = (Typename)semanticStack.pop();
		Identifier id = (Identifier)semanticStack.pop();
			
		semanticStack.push(new Member(id, tn, lineNumber()));			
	}

	public String toString() { return actionName; }
}
