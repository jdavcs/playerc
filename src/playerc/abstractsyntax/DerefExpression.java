package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 20 2005
 * modified Sep 04 2017
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
