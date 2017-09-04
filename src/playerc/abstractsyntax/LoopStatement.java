package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
