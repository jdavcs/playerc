/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.util.Stack;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class PushSymbol implements ParseAction {
  private String symbol;

  public PushSymbol(String symbol) {
    this.symbol = symbol;
  }

  public void execute(Stack stack) {
    stack.push(symbol);
  }

  public String toString() {
    return " " + symbol;
  }
}
