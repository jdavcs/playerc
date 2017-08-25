package playerc;

import java.util.Stack;

/**
 * Author: sergei
 * Created: Jul 19, 2005
 */
public interface ParseAction
{
   public void execute(Stack s);
   public String toString();   
}
