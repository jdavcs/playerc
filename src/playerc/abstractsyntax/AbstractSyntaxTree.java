package playerc.abstractsyntax;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 06 2005
 * modified Sep 04 2017
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
