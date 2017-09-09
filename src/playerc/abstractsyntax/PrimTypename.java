/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class PrimTypename extends Typename {
  private String name;

  public PrimTypename(String name, int lineNumber) {
    super(lineNumber);
    this.name = trim(name);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public String toString() {
    return name;
  }

  private String trim(String s) {
    if (s.startsWith("'"))
      return s.substring(1, s.length() - 1);
    else
      return s;
  }
}
