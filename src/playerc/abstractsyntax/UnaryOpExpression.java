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
public abstract class UnaryOpExpression extends Expression {
  private Expression exp;

  public UnaryOpExpression(Expression exp, int lineNumber) {
    super(lineNumber);
    this.exp = exp;
  }

  public Expression expression() {
    return exp;
  }
}
