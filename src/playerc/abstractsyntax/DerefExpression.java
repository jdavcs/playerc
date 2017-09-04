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
public class DerefExpression extends Expression {
  private Lvalue lvalue;
  private Identifier id;

  public DerefExpression(Lvalue lvalue, Identifier id, int lineNumber) {
    super(lineNumber);
    this.lvalue = lvalue;
    this.id = id;
  }

  public Lvalue lvalue() {
    return lvalue;
  }

  public Identifier id() {
    return id;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
