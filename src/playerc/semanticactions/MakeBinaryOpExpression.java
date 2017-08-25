package playerc.semanticactions;

import java.util.Stack;
import playerc.*;
import playerc.abstractsyntax.*;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 2:20:11 PM
 */
public class MakeBinaryOpExpression extends SemanticAction
{
	private String actionName;

	public MakeBinaryOpExpression(String actionName, int lineNumber)
	{ 
		super(lineNumber);
		this.actionName = actionName;
	}

	public void execute(Stack semanticStack, Token lastToken)
	{
	      Expression right = (Expression)semanticStack.pop();
	      Operator op = (Operator)semanticStack.pop();
	      Expression left = (Expression)semanticStack.pop();      
	      Expression result = null;
	      
	      if (op.type() == PlayerTokens.MinusOp)            
	         result = new MinusExpression(left, right, lineNumber());
	      else if (op.type() == PlayerTokens.PlusOp)
	         result = new PlusExpression(left, right, lineNumber());        
	      else if (op.type() == PlayerTokens.MultOp)
	         result = new MultExpression(left, right, lineNumber());  
	      else if (op.type() == PlayerTokens.DivOp)
	         result = new DivExpression(left, right, lineNumber());  
	      
	      else if (op.type() == PlayerTokens.KeyAnd)
	         result = new AndExpression(left, right, lineNumber());  
	      else if (op.type() == PlayerTokens.KeyOr)
	         result = new OrExpression(left, right, lineNumber());  

	      else if (op.type() == PlayerTokens.Less)
	         result = new LessExpression(left, right, lineNumber());  
	      else if (op.type() == PlayerTokens.LessEql)
	         result = new LessEqlExpression(left, right, lineNumber());  
	      else if (op.type() == PlayerTokens.Greater)
	         result = new GreaterExpression(left, right, lineNumber());  
	      else if (op.type() == PlayerTokens.GreaterEql)
	         result = new GreaterEqlExpression(left, right, lineNumber());  
	      else if (op.type() == PlayerTokens.Eql)
	         result = new EqlExpression(left, right, lineNumber());  
	      else if (op.type() == PlayerTokens.NotEql)
	         result = new NotEqlExpression(left, right, lineNumber());  
	      
	      left.setParent(result);
	      right.setParent(result);      
	      semanticStack.push(result);
	}

	public String toString() { return actionName; }
}
