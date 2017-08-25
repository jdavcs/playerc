package playerc.abstractsyntax;


/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class CallExpression extends Expression
{
	private Identifier id;

	private ExpressionList params;

	public CallExpression(Identifier id, ExpressionList params, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.params = params;
	}

	public void accept(Visitor v) { v.visit(this); }
	
	public Identifier id() { return id; }
	
	public ExpressionList params() { return params; }
}