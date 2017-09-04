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
public class LessEqlExpression extends BinaryOpExpression {
  public LessEqlExpression(Expression left, Expression right, int lineNumber) {
    super(left, right, lineNumber);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}