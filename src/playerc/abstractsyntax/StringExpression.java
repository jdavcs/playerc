package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
