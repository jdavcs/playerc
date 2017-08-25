package playerc;

import java.util.Stack;

/**
 * Author: sergei
 * Created: Jul 21, 2005
 */
public class PushNothing implements ParseAction
{
   public PushNothing() {}
   
   public void execute(Stack stack) {}

   public String toString() { return "(empty)"; }
}
