package playerc;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jun 17 2005
 * modified Sep 04 2017
 */
public class LexicalException extends Exception
{
    public LexicalException( String s ) { super(s); }

    public String toString()
    {
        return "LEXICAL EXCEPTION -- " + super.toString();
    }
}