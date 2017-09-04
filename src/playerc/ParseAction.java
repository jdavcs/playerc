package playerc;

import java.util.Stack;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jul 19 2005
 * modified Sep 04 2017
 */
public interface ParseAction
{
   public void execute(Stack s);
   public String toString();   
}
