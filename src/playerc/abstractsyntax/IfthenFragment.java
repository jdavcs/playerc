package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class IfthenFragment extends AbstractSyntaxTree
{
	private Expression exp;
	private StatementList stms;
	
	public IfthenFragment(Expression exp, StatementList stms, int lineNumber)
	{
		super(lineNumber);
		this.exp = exp;
		this.stms = stms;
	}

	public Expression expression() { return exp; }
	
	public StatementList statements() { return stms; }
	
	public void accept(Visitor v) { v.visit(this); }
}
