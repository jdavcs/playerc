package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class IntegerExpression extends Expression
{
	private int value;
	
	public IntegerExpression(int value, int lineNumber)
	{
		super(lineNumber);
		this.value = value;
	}
	
	public int value() { return value; }

	public void accept(Visitor v) { v.visit(this); }
}
