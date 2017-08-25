package playerc;

import java.util.Stack;


/**
 * Author: sergei
 * Created: Jul 19, 2005
 */


public class PushSequence implements ParseAction
{
   private ParseAction[] actions;

   public PushSequence(ParseAction[] actions) { this.actions = actions; }

   public void execute( Stack stack )
   {
      for (int i = actions.length-1; i >= 0; i--)
          actions[i].execute( stack );
   }

   public String toString()
   {
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < actions.length; i++)
         buffer.append(actions[i].toString() + " ");
      return buffer.toString();
   }
}
