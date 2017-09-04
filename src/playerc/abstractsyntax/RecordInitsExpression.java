package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
