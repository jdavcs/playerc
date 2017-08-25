package playerc;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 23, 2006 9:33:36 PM
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
