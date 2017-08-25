package playerc;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 23, 2006 9:33:25 PM
 */
public class PrimitiveDataType extends DataType
{	
	public PrimitiveDataType(int type, int line) throws SemanticException
	{
		super(type, line);	
		if (type > ERROR) 
			throw new SemanticException("invalid type enumeration value for primitive type: " + type, line);
	}
	
	public boolean equals(DataType dt) { return type() == dt.type(); }
	
	public String name()
	{
		if (type() == INTEGER)
			return INTEGER_NAME;
		else if (type() == REAL)
			return REAL_NAME;
		else if (type() == BOOLEAN)
			return BOOLEAN_NAME;
		else if (type() == STRING)
			return STRING_NAME;
		else if (type() == NULL)
			return NULL_NAME;
		else if (type() == VOID)
			return VOID_NAME;
		else
			return ERROR_NAME;
	}
}
