package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class RecordInitsExpression extends Expression
{
	private Identifier id;
	private RecordInitList inits;
	
	public RecordInitsExpression(Identifier id, RecordInitList inits, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.inits = inits;
	}
	
	public Identifier id() { return id; }
	
	public RecordInitList recordInits() { return inits; }

	public void accept(Visitor v) { v.visit(this); }
}
