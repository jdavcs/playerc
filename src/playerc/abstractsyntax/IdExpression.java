package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 20, 2005
 */
public class IdExpression extends Expression
{
   private Identifier id;
   
   public IdExpression(Identifier id, int lineNumber) 
   {
      super(lineNumber);
      this.id = id; 
   }
   
   public Identifier id() { return id; }
   
   public void accept(Visitor v) { v.visit(this); }  
}
