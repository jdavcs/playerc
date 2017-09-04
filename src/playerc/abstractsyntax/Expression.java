package playerc.abstractsyntax;


/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 14 2005
 * modified Sep 04 2017
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
