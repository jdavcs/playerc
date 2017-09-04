package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class AssignmentStatement extends Statement
{
	private Lvalue lvalue;
	private Expression exp;
	
	public AssignmentStatement(Lvalue lvalue, Expression exp, int lineNumber)
	{
		super(lineNumber);
		this.lvalue = lvalue;
		this.exp = exp;		
	}
	
	public Lvalue lvalue() { return lvalue; }
	
	public Expression expression() { return exp; }

	public void accept(Visitor v) { v.visit(this); }
}
