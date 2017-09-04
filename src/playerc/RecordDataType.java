package playerc;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  May 23 2006
 * modified Sep 04 2017
 */
public class RecordDataType extends ConstructedDataType
{
	public RecordDataType(String name, int line) throws SemanticException
	{
		super(name, DataType.RECORD, line);	
//must store a collection of tuples: DataType x String
	}
}
