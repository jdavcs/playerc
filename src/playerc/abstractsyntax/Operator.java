package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
