package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class LookupLvalue extends Lvalue
{
	private Lvalue lval;
	private Expression exp;
	
	public LookupLvalue(Lvalue lval, Expression exp, int lineNumber)
	{
		super(lineNumber);
		this.lval = lval;
		this.exp = exp;
	}
	
	public Lvalue lvalue() { return lval; }
	
	public Expression expression() { return exp; }

	public void accept(Visitor v) { v.visit(this); }
}
