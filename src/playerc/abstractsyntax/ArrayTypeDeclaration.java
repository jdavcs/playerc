package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class ArrayTypeDeclaration extends Declaration
{
	private Identifier id;
	private Typename typename;
	
	public ArrayTypeDeclaration(Identifier id, Typename typename, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.typename = typename;		
	}
	
	public Identifier id() { return id; }
	
	public Typename typename() { return typename; }

	public void accept(Visitor v) { v.visit(this); }
}
