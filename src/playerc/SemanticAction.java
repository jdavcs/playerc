package playerc;

import java.util.Stack;

/**
 * Author: sergei
 * Created: Aug 11, 2005
 */
public abstract class SemanticAction
{
   private int lineNumber;
   
   public SemanticAction(int lineNumber) { this.lineNumber = lineNumber; }
   
   protected int lineNumber() { return lineNumber; }
   
   public abstract void execute(Stack semanticStack, Token lastToken) throws SemanticException;
   
   public abstract String toString();
}