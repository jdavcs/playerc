package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 20 2005
 * modified Sep 04 2017
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
