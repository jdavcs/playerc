package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
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
