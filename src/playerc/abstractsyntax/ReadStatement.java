package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
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
