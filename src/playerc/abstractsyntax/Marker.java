package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class Marker extends AbstractSyntaxTree
{
	public Marker(int lineNumber)
	{
		super(-1);
	}

	public void accept(Visitor v) {}
}
