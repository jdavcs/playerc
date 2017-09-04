package playerc;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 * created  May 23 2006
 * modified Sep 04 2017
 */
public abstract class DataType
{
	public static int INTEGER = 0;
	public static int REAL = 1;
	public static int BOOLEAN = 2;
	public static int STRING = 3;	
	public static int NULL = 4;
	public static int VOID = 5;
	public static int ERROR = 6;
	public static int ARRAY = 7;
	public static int RECORD = 8;	
	
	public static String INTEGER_NAME = "integer";
	public static String REAL_NAME = "real";
	public static String BOOLEAN_NAME = "boolean";
	public static String STRING_NAME = "string";
	public static String NULL_NAME = "null";
	public static String VOID_NAME = "void";
	public static String ERROR_NAME = "error";
	
	private int type;
	
	public DataType(int type, int line) throws SemanticException
	{
		if (type < INTEGER || type > RECORD) 
			throw new SemanticException("invalid type enumeration value: " + type, line);
		this.type = type;		
	}
	
	protected int type() { return type; }
	
	public boolean equals(DataType dt) { return (type == dt.type()); }
	
	public int hashcode() { return name().hashCode(); }
	
	public abstract String name();
}
