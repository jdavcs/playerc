/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

import java.util.Stack;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */

public class PushSequence implements ParseAction {
  private ParseAction[] actions;

  public PushSequence(ParseAction[] actions) {
    this.actions = actions;
  }

  public void execute(Stack stack) {
    for (int i = actions.length - 1; i >= 0; i--)
      actions[i].execute(stack);
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < actions.length; i++)
      buffer.append(actions[i].toString() + " ");
    return buffer.toString();
  }
}
