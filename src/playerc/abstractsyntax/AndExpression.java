package playerc.abstractsyntax;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 15, 2006 7:51:14 PM
 */
public class AndExpression extends BinaryOpExpression
{
	public AndExpression(Expression left, Expression right, int lineNumber)
	{
		super(left, right, lineNumber); 
	}
	
	public void accept(Visitor v) { v.visit(this); }   
}