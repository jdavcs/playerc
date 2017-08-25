package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class ProcedureDeclaration extends Declaration
{
	private Identifier id;
	private FormalParams params;
	private Typename typename;
	private DeclarationList decls;
	private StatementList stms;
	
	public ProcedureDeclaration(Identifier id, FormalParams params, Typename typename, DeclarationList decls, StatementList stms, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.params = params;
		this.typename = typename;
		this.decls = decls;
		this.stms = stms;
	}
	
	public Identifier id() { return id; }
	
	public FormalParams params() { return params; }
	
	public Typename typename() { return typename; }
	
	public DeclarationList declarations() { return decls; }
	
	public StatementList statements() { return stms; }

	public void accept(Visitor v) { v.visit(this); }
}
