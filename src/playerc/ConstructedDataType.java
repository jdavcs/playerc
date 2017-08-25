package playerc;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	May 23, 2006 10:08:56 PM
 */
public abstract class ConstructedDataType extends DataType
{
	private String name;
	
	public ConstructedDataType(String name, int type, int line) throws SemanticException
	{
		super(type, line);
		if (type < ARRAY) 
			throw new SemanticException("invalid type enumeration value for constructed type: " + type, line);
		this.name = name;
	}
	
	public boolean equals(DataType dt) { return name == dt.name(); }
	
	public String name() { return name; }
}
