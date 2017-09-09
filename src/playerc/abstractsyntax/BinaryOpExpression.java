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
public abstract class BinaryOpExpression extends Expression {
  private Expression left;
  private Expression right;

  public BinaryOpExpression(Expression left, Expression right, int lineNumber) {
    super(lineNumber);
    this.left = left;
    this.right = right;
  }

  public Expression left() {
    return left;
  }

  public Expression right() {
    return right;
  }
}
