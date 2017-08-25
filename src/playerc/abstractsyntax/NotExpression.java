package playerc.abstractsyntax;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 15, 2006 7:58:02 PM
 */
public class NotExpression extends UnaryOpExpression
{   
	public NotExpression(Expression exp, int lineNumber) 
	{ 
		super(exp, lineNumber);
	}
	
	public void accept(Visitor v) { v.visit(this); }
}
