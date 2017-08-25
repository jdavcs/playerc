package playerc.abstractsyntax;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 15, 2006 7:52:17 PM
 */
public class LessExpression extends BinaryOpExpression
{
	public LessExpression(Expression left, Expression right, int lineNumber)
	{
		super(left, right, lineNumber); 
	}
	
	public void accept(Visitor v) { v.visit(this); }   
}