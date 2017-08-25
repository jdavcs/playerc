package playerc.abstractsyntax;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 15, 2006 7:53:16 PM
 */
public class EqlExpression extends BinaryOpExpression
{
	public EqlExpression(Expression left, Expression right, int lineNumber)
	{
		super(left, right, lineNumber); 
	}
	
	public void accept(Visitor v) { v.visit(this); }   
}