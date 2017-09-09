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
public class ReturnStatement extends Statement {
  private Expression exp;

  public ReturnStatement(Expression e, int lineNumber) {
    super(lineNumber);
    exp = e;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Expression expression() {
    return exp;
  }
}
