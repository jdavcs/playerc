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
public class ArrayType extends NewType {
  private Typename elementTypename;

  public ArrayType(Typename t, int lineNumber) {
    super(lineNumber);
    elementTypename = t;
  }

  public Typename elementTypename() {
    return elementTypename;
  }

  public void accept(Visitor v) {
  }
}
