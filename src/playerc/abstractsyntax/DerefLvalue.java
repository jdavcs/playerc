package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class DerefLvalue extends Lvalue
{
	private Lvalue lval;
	private Identifier id;
		
	public DerefLvalue(Lvalue lval, Identifier id, int lineNumber)
	{
		super(lineNumber);
		this.lval = lval;
		this.id = id;
	}
	
	public Lvalue lvalue() { return lval; }
	
	public Identifier id() { return id; }

	public void accept(Visitor v) { v.visit(this); }
}
