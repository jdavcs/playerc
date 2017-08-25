package playerc;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 23, 2006 9:33:42 PM
 */
public class RecordDataType extends ConstructedDataType
{
	public RecordDataType(String name, int line) throws SemanticException
	{
		super(name, DataType.RECORD, line);	
//must store a collection of tuples: DataType x String
	}
}
