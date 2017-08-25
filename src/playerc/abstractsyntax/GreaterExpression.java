package playerc.abstractsyntax;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 15, 2006 7:52:48 PM
 */
public class GreaterExpression extends BinaryOpExpression
{
	public GreaterExpression(Expression left, Expression right, int lineNumber)
	{
		super(left, right, lineNumber); 
	}
	
	public void accept(Visitor v) { v.visit(this); }   
}