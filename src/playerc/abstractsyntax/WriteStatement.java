package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class WriteStatement extends Statement
{
	private ExpressionList exps;
	
	public WriteStatement(ExpressionList exps, int lineNumber)
	{
		super(lineNumber);
		this.exps = exps;
	}
	
	public ExpressionList expressions() { return exps; }

	public void accept(Visitor v) { v.visit(this); }
}
