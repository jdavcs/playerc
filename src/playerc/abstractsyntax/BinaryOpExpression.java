package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
 */
public abstract class BinaryOpExpression extends Expression
{
   private Expression left;
   private Expression right;
   
   public BinaryOpExpression(Expression left, Expression right, int lineNumber) 
   {
      super(lineNumber);
      this.left = left;
      this.right = right;
   }
   
   public Expression left() { return left; }
   
   public Expression right() { return right; }   
}
