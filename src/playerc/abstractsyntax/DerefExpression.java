package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 20, 2005
 */
public class DerefExpression extends Expression
{
   private Lvalue lvalue;
   private Identifier id;
   
   public DerefExpression(Lvalue lvalue, Identifier id, int lineNumber) 
   {
      super(lineNumber);
      this.lvalue = lvalue; 
      this.id = id; 
   }
      
   public Lvalue lvalue() { return lvalue; }
   
   public Identifier id() { return id; }
   
   public void accept(Visitor v) { v.visit(this); }  
}
