package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class ArrayInitsExpression extends Expression
{
	private Identifier id;
	private ExpressionList list;
	
	public ArrayInitsExpression(Identifier id, ExpressionList list, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.list = list;
	}
	
	public Identifier id() { return id; }
	
	public ExpressionList expressions() { return list; }

	public void accept(Visitor v) { v.visit(this); }
}
