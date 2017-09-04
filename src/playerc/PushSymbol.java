package playerc;

import java.util.Stack;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Jul 21 2005
 * modified Sep 04 2017
 */
public class PushSymbol implements ParseAction
{
   private String symbol;

   public PushSymbol(String symbol) { this.symbol = symbol; }

   public void execute(Stack stack) { stack.push(symbol); }

   public String toString() { return " " +  symbol; }
}
