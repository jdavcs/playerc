package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
