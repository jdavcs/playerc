package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class RecordInit extends AbstractSyntaxTree
{
	private Identifier id;
	private Expression exp;
	
	public RecordInit(Identifier id, Expression exp, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.exp = exp;
	}
	
	public Identifier id() { return id; }
	
	public Expression expression() { return exp; }

	public void accept(Visitor v) { v.visit(this); }
}
