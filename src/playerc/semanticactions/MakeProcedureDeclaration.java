package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeProcedureDeclaration extends SemanticAction
{
	private String actionName;

	public MakeProcedureDeclaration(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		StatementList statements = (StatementList)semanticStack.pop();
		DeclarationList declarations = (DeclarationList)semanticStack.pop();
		
		Typename typename = null;
		if (semanticStack.peek() instanceof Typename)
			typename = (Typename)semanticStack.pop();
		
		FormalParams params = (FormalParams)semanticStack.pop();
		Identifier id = (Identifier)semanticStack.pop();
		
		semanticStack.push(new ProcedureDeclaration(id, params, typename, declarations, statements, lineNumber()));		
	}

	public String toString() { return actionName; }
}
