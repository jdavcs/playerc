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
public class MakeUnaryOpExpression extends SemanticAction
{
	private String actionName;

	public MakeUnaryOpExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      Expression e = (Expression)semanticStack.pop();
	      Operator op = (Operator)semanticStack.pop();
	      
	      if (op.type() == PlayerTokens.MinusOp)
	         semanticStack.push(new NegativeExpression(e, lineNumber()));
	      else if (op.type() == PlayerTokens.PlusOp)
	         semanticStack.push(new PositiveExpression(e, lineNumber()));
	      else if (op.type() == PlayerTokens.KeyNot)
	         semanticStack.push(new NotExpression(e, lineNumber()));     
	}

	public String toString() { return actionName; }
}
