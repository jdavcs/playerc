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
public class ParenExpression extends Expression {
  Expression exp;

  public ParenExpression(Expression exp, int lineNumber) {
    super(lineNumber);
    this.exp = exp;
  }

  public Expression exp() {
    return exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
