package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
