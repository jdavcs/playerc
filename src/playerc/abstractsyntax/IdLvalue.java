package playerc.abstractsyntax;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 14, 2006 9:33:26 PM
 */
public class IdLvalue extends Lvalue
{
	private Identifier id;
	
	public IdLvalue(Identifier id, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
	}
	
	public Identifier id() { return id; }

	public void accept(Visitor v) { v.visit(this); }
}
