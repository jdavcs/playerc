package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class RealExpression extends Expression
{
	private double value;
	
	public RealExpression(double value, int lineNumber)
	{
		super(lineNumber);
		this.value = value;
	}
	
	public double value() { return value; }

	public void accept(Visitor v) { v.visit(this); }
}
