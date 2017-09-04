package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class VarDeclaration extends Declaration
{
	private Identifier id;
	private Typename tn;
	private Expression exp;
	
	public VarDeclaration(Identifier id, Typename tn, Expression exp, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.tn = tn;
		this.exp = exp;
	}
	
	public Identifier id() { return id; }
	
	public Typename typename() { return tn; }
	
	public Expression expression() { return exp; }

	public void accept(Visitor v) { v.visit(this); }
	
	public String toString() { return "var decl"; }
}
