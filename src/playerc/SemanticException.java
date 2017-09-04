package playerc;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Aug 26 2005
 * modified Sep 04 2017
 */
public class SemanticException extends Exception
{
   private int lineNumber;
   
   public SemanticException(String s, int line) 
   { 
      super(s); 
      lineNumber = line; 
   }

   public String toString()
   {
       return "LINE " + lineNumber + ": " + super.toString();
   }
}