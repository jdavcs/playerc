package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeProgram extends SemanticAction
{
	private String actionName;

	public MakeProgram(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		StatementList statements = (StatementList)semanticStack.pop();
		DeclarationList declarations = (DeclarationList)semanticStack.pop();
		Identifier id = (Identifier)semanticStack.pop();
		
		semanticStack.push(new Program(id, declarations, statements, lineNumber()));			
	}

	public String toString() { return actionName; }
}
