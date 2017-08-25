package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class ForStatement extends Statement
{
	private Identifier id; 
	private Expression expFrom;
	private Expression expTo;
	private Expression expBy;
	private StatementList stms;
	
	public ForStatement(Identifier id, Expression expFrom, Expression expTo, Expression expBy, StatementList stms, int lineNumber)
	{
		super(lineNumber);
	}
	
	public Identifier id() { return id; }
	
	public Expression expressionFrom() { return expFrom; }
	
	public Expression expressionTo() { return expTo; }
	
	public Expression expressionBy() { return expBy; }
	
	public StatementList statements() { return stms; }

	public void accept(Visitor v) { v.visit(this); }
}
