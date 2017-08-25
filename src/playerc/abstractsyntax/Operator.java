package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class Operator extends AbstractSyntaxTree
{
	private int type;
	
	public Operator(int type, int lineNumber)
	{
		super(lineNumber);
		this.type = type;
	}
	
	public int type() { return type; }

	public void accept(Visitor v) { v.visit(this); }
}
