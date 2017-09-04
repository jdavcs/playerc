package playerc;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jul 19 2005
 * modified Sep 04 2017
 */
public class ParsingException extends Exception
{
   public ParsingException( String s ) { super(s); }

   public String toString()
   {
       return "PARSING EXCEPTION -- " + super.toString();
   }
}
