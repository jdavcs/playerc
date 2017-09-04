package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class Identifier extends AbstractSyntaxTree
{
	private String value;
	
	public Identifier(String value, int lineNumber)
	{
		super(lineNumber);
		this.value = value;
	}
	
	public String toString() { return value; }

	public void accept(Visitor v) { v.visit(this); }
}
