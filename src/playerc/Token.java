package playerc;

/**
 * Author: sergei
 * Created: Jun 17, 2005
 */

public class Token 
{	
	private int type;
	private int ival;
	private double dval;
	private String sval;

	public Token(int type) { this(type, 0, 0, ""); }
	
	public Token(int type, int value) { this(type, value, 0, ""); }
		
	public Token(int type, double value) { this(type, 0, value, ""); }
		
	public Token(int type, String value) { this(type, 0, 0, value); }
	
	public String toString() //check this
	{
		if (type == 1)
			return type + "(" + ival + ")";
		else if (type == 2)
			return type + "(" + dval + ")";		
		else if((type == 3) || (type == 4))
			return type + "(" + sval + ")";	
		else
		   return "" + type;
	}
	
	public int type() { return type; }
	
	public int getIValue() { return ival; }
	
	public double getDValue() { return dval; }
	
	public String getSValue() { return sval; }	

	private Token(int type, int iv, double dv, String sv)
	{
	   this.type = type;
	   ival = iv;
	   dval = dv;
	   sval = sv;
	}
}