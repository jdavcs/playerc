package playerc;

/**
 * Author: sergei
 * Created: Aug 14, 2005
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