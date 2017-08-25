package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 21, 2005
 */
public class MinusExpression extends BinaryOpExpression
{
   public MinusExpression(Expression left, Expression right, int lineNumber)
   {
      super(left, right, lineNumber); 
   }
   
   public void accept(Visitor v) { v.visit(this); }   
}
