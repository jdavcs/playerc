package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 15 2006
 * modified Sep 04 2017
 */
public class NegativeExpression extends UnaryOpExpression
{   
	public NegativeExpression(Expression exp, int lineNumber) 
	{ 
		super(exp, lineNumber);
	}
	
	public void accept(Visitor v) { v.visit(this); }
}
