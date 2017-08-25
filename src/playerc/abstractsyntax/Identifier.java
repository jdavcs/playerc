package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
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
