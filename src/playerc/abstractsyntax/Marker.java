package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class Marker extends AbstractSyntaxTree
{
	public Marker(int lineNumber)
	{
		super(-1);
	}

	public void accept(Visitor v) {}
}
