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
public class MakeRecordTypeDeclaration extends SemanticAction
{
	private String actionName;

	public MakeRecordTypeDeclaration(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		MemberList list = (MemberList)semanticStack.pop();
		Identifier id = (Identifier)semanticStack.pop();
		
		semanticStack.push(new RecordTypeDeclaration(id, list, lineNumber()));
	}

	public String toString() { return actionName; }
}
