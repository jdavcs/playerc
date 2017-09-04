package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class ReturnStatement extends Statement
{
	private Expression exp;
	
	public ReturnStatement(Expression exp, int lineNumber)
	{
		super(lineNumber);
		this.exp = exp;
	}
	
	public Expression expression() { return exp; }

	public void accept(Visitor v) { v.visit(this); }
}
