package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 21 2005
 * modified Sep 04 2017
 */
public class MinusExpression extends BinaryOpExpression
{
   public MinusExpression(Expression left, Expression right, int lineNumber)
   {
      super(left, right, lineNumber); 
   }
   
   public void accept(Visitor v) { v.visit(this); }   
}
