package playerc;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1 
 * created  May 23 2006
 * modified Sep 04 2017
 */
public class ArrayDataType extends ConstructedDataType
{
	private DataType elementType;
	
	public ArrayDataType(String name, DataType elementType, int line) throws SemanticException
	{
		super(name, DataType.ARRAY, line);	
		this.elementType = elementType;
	}
	
	public DataType elementType() { return elementType; }
}
