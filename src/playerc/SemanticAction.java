package playerc;

import java.util.Stack;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 26 2005
 * modified Sep 04 2017
 */
public abstract class SemanticAction
{
   private int lineNumber;
   
   public SemanticAction(int lineNumber) { this.lineNumber = lineNumber; }
   
   protected int lineNumber() { return lineNumber; }
   
   public abstract void execute(Stack semanticStack, Token lastToken) throws SemanticException;
   
   public abstract String toString();
}