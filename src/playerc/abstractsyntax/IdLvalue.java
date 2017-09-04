package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 15 2006
 * modified Sep 04 2017
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
