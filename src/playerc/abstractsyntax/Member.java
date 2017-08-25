package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class Member extends AbstractSyntaxTree
{
	private Identifier id;
	private Typename tn;
	
	public Member(Identifier id, Typename tn, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.tn = tn;
	}
	
	public Identifier id() { return id; }
	
	public Typename typename() { return tn; }

	public void accept(Visitor v) { v.visit(this); }
}
