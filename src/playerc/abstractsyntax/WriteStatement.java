package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
