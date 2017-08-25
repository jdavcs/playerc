package playerc.abstractsyntax;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 15, 2006 7:57:55 PM
 */
public class PositiveExpression extends UnaryOpExpression
{   
	public PositiveExpression(Expression exp, int lineNumber) 
	{ 
		super(exp, lineNumber);
	}
	
	public void accept(Visitor v) { v.visit(this); }
}
