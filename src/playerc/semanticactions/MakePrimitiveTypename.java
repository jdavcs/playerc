package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakePrimitiveTypename extends SemanticAction
{
	private String actionName;

	public MakePrimitiveTypename(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
		String primtype = new PlayerTokens().getTokenName(lastToken.type());
		semanticStack.push(new PrimitiveTypename(primtype, lineNumber()));
	}

	public String toString() { return actionName; }
}
