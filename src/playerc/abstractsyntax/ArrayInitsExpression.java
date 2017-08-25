package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
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
