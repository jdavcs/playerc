package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
