package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class IfStatement extends Statement
{
	private IfthenFragmentList ifthenFrags;
	private ElseFragment elseFrag;
	
	public IfStatement(IfthenFragmentList ifthenFrags, ElseFragment elseFrag, int lineNumber)
	{
		super(lineNumber);
		this.ifthenFrags = ifthenFrags;
		this.elseFrag = elseFrag;
	}
	
	public IfthenFragmentList ifthenFrags() { return ifthenFrags; }
	
	public ElseFragment elseFrag() { return elseFrag; }

	public void accept(Visitor v) { v.visit(this); }
}
