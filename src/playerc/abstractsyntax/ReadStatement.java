package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class ReadStatement extends Statement
{
	private LvalueList lvals;
	
	public ReadStatement(LvalueList lvals, int lineNumber)
	{
		super(lineNumber);
		this.lvals = lvals;
	}
	
	public LvalueList lvalues() { return lvals; }

	public void accept(Visitor v) { v.visit(this); }
}
