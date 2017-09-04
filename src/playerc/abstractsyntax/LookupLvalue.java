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
public class LookupLvalue extends Lvalue {
  private Lvalue lval;
  private Expression exp;

  public LookupLvalue(Lvalue lval, Expression exp, int lineNumber) {
    super(lineNumber);
    this.lval = lval;
    this.exp = exp;
  }

  public Lvalue lvalue() {
    return lval;
  }

  public Expression expression() {
    return exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
