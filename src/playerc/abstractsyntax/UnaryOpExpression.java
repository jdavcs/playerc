package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public abstract class UnaryOpExpression extends Expression
{
	private Expression exp; 
	
	public UnaryOpExpression(Expression exp, int lineNumber)
	{
		super(lineNumber);
		this.exp = exp;
	}
	
	public Expression expression() { return exp; }
}
