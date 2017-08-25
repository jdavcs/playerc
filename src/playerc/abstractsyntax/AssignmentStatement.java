package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
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
