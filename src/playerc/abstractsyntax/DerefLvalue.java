package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
