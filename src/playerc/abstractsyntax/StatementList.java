package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public class StatementList extends List
{
	public StatementList(int lineNumber)
	{
		super(lineNumber);
	}

	public void accept(Visitor v) { v.visit(this); }
}
