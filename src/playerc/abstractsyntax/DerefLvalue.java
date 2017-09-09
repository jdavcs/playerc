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
public class DerefLvalue extends Lvalue {
  private Lvalue lvalue;
  private Identifier id;

  public DerefLvalue(Lvalue lvalue, Identifier id, int lineNumber) {
    super(lineNumber);
    this.lvalue = lvalue;
    this.id = id;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Lvalue lvalue() {
    return lvalue;
  }

  public Identifier id() {
    return id;
  }
}
