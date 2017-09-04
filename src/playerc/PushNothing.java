package playerc;

import java.util.Stack;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jul 21 2005
 * modified Sep 04 2017
 */
public class PushNothing implements ParseAction
{
   public PushNothing() {}
   
   public void execute(Stack stack) {}

   public String toString() { return "(empty)"; }
}
