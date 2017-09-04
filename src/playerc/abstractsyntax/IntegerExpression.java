package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
