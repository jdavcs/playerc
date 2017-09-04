package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class ExpressionList extends List
{
	public ExpressionList(int lineNumber)
	{
		super(lineNumber);
	}

	public void accept(Visitor v) { v.visit(this); }
}
