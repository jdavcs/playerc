package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class FormalParams extends AbstractSyntaxTree
{
	private FPSectionList fpsections;
	
	public FormalParams(FPSectionList fpsections, int lineNumber)
	{
		super(lineNumber);
		this.fpsections = fpsections;
	}
	
	public FPSectionList fpsections() { return fpsections; }

	public void accept(Visitor v) { v.visit(this); }
}
