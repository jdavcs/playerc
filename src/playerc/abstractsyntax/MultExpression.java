package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 15 2006
 * modified Sep 04 2017
 */
public class MultExpression extends BinaryOpExpression
{
	public MultExpression(Expression left, Expression right, int lineNumber)
	{
		super(left, right, lineNumber); 
	}
	
	public void accept(Visitor v) { v.visit(this); }   
}