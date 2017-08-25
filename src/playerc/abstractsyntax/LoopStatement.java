package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class LoopStatement extends Statement
{
	private StatementList stms;
	
	public LoopStatement(StatementList stms, int lineNumber)
	{
		super(lineNumber);
		this.stms = stms;
	}
	
	public StatementList statements() { return stms; }

	public void accept(Visitor v) { v.visit(this); }
}
