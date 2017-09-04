package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
