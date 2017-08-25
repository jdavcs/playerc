package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class PrimitiveTypename extends Typename
{
	private String name;
	
	public PrimitiveTypename(String name, int lineNumber)
	{
		super(lineNumber);
		this.name = name;
	}
	
	public String name() { return name; }

	public void accept(Visitor v) { v.visit(this); }
}
