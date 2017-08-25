package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 20, 2005
 */
public class LookupExpression extends Expression
{
   private Lvalue lvalue;   
   private Expression exp;
   
   public LookupExpression(Lvalue lvalue, Expression exp, int lineNumber) 
   {
      super(lineNumber);
      this.lvalue = lvalue; 
      this.exp = exp; 
   }   
      
   public Lvalue lvalue() { return lvalue; }
   
   public Expression expression() { return exp; }
   
   public void accept(Visitor v) { v.visit(this); }  
}

