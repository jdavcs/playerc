/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class PrimitiveTypename extends Typename {
  private String name;

  public PrimitiveTypename(String name, int lineNumber) {
    super(lineNumber);
    this.name = name;
  }

  public String name() {
    return name;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
