package playerc;

/**
 * Author: sergei
 * Created: Jun 17, 2005
 */
public class LexicalException extends Exception
{
    public LexicalException( String s ) { super(s); }

    public String toString()
    {
        return "LEXICAL EXCEPTION -- " + super.toString();
    }
}