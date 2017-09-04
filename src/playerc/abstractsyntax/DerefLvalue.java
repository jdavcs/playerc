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

public class DerefLvalue extends Lvalue {
  private Lvalue lval;
  private Identifier id;

  public DerefLvalue(Lvalue lval, Identifier id, int lineNumber) {
    super(lineNumber);
    this.lval = lval;
    this.id = id;
  }

  public Lvalue lvalue() {
    return lval;
  }

  public Identifier id() {
    return id;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
