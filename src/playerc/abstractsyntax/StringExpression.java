package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class StringExpression extends Expression
{
	private String value;
	
	public StringExpression(String value, int lineNumber)
	{
		super(lineNumber);
		this.value = value;
	}
	
	public String value() { return value; }

	public void accept(Visitor v) { v.visit(this); }
}
