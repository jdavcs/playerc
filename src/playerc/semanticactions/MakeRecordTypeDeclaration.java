package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
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
