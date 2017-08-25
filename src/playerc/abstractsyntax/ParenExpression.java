package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class ParenExpression extends Expression
{
	private Expression exp;
	
	public ParenExpression(Expression exp, int lineNumber)
	{
		super(lineNumber);
		this.exp = exp;
	}
	
	public Expression expression() { return exp; }

	public void accept(Visitor v) { v.visit(this); }
}
