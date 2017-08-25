package playerc;

import java.util.Stack;

/**
 * Author: sergei
 * Created: Jul 21, 2005
 */
public class PushSymbol implements ParseAction
{
   private String symbol;

   public PushSymbol(String symbol) { this.symbol = symbol; }

   public void execute(Stack stack) { stack.push(symbol); }

   public String toString() { return " " +  symbol; }
}
