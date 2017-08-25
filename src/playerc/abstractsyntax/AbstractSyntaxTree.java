package playerc.abstractsyntax;

/**
 * Author: sergei
 * Created: Aug 6, 2005
 */
public abstract class AbstractSyntaxTree
{   
   private int lineNumber;
   
   public AbstractSyntaxTree(int lineNumber) { this.lineNumber = lineNumber; }
   
   public abstract void accept(Visitor v);
   
   public String toString() { return "AST"; }
   
   public void setLineNumber(int number) { lineNumber = number; }
   
   public int lineNumber() { return lineNumber; }
}
