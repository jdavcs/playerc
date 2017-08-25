package playerc;

/**
 * Author: sergei
 * Created: Jul 19, 2005
 */
public class ParsingException extends Exception
{
   public ParsingException( String s ) { super(s); }

   public String toString()
   {
       return "PARSING EXCEPTION -- " + super.toString();
   }
}
