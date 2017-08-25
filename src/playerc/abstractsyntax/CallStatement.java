package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public class CallStatement extends Statement
{
	   private Identifier id;
	   private ExpressionList params;
	   
	   public CallStatement(Identifier id, ExpressionList params, int lineNumber) 
	   {
	      super(lineNumber);
	      this.id = id; 
	      this.params = params;       
	   } 
	      
	   public void accept(Visitor v) { v.visit(this); }  
	   
	   public Identifier id() { return id; }
	   
	   public ExpressionList params() { return params; } 
	}
