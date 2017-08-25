package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class RecordTypeDeclaration extends Declaration
{
	private Identifier id;
	private MemberList members;
	
	public RecordTypeDeclaration(Identifier id, MemberList members, int lineNumber)
	{
		super(lineNumber);
		this.id = id;
		this.members = members;
	}
	
	public Identifier id() { return id; }
	
	public MemberList members() { return members; }

	public void accept(Visitor v) { v.visit(this); }
}
