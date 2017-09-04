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
public class LookupExpression extends Expression {
  private Lvalue lvalue;
  private Expression exp;

  public LookupExpression(Lvalue lvalue, Expression exp, int lineNumber) {
    super(lineNumber);
    this.lvalue = lvalue;
    this.exp = exp;
  }

  public Lvalue lvalue() {
    return lvalue;
  }

  public Expression expression() {
    return exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
