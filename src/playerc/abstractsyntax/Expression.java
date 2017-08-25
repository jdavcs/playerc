package playerc.abstractsyntax;


/**
 * Author: sergei
 * Created: Aug 14, 2005
 */
public abstract class Expression extends AbstractSyntaxTree
{
   private Expression parent;
   
   public Expression(int lineNumber) 
   { 
      super(lineNumber); 
   }
   
   public Expression parent() { return parent; }
   
   public void setParent(Expression parent) { this.parent = parent; }
}
