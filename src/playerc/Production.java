/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

import java.util.*;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class Production {
  private String lhs;
  private Vector rhs;

  public Production(String l, Vector r) throws Exception {
    if (l == null || r == null)
      throw new Exception("production must have a LHS and RHS");
    lhs = l;
    rhs = r;
  }

  public String lhs() {
    return lhs;
  }

  public Vector rhs() {
    return rhs;
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer(lhs + " -> ");
    for (int i = 0; i < rhs.size(); i++)
      buffer.append(rhs.elementAt(i) + " ");
    buffer.append("\n");
    return buffer.toString();
  }
}