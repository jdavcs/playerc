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
public class ByExpressionOpt extends AbstractSyntaxTree {
  private Expression exp;

  public ByExpressionOpt(Expression e, int lineNumber) {
    super(lineNumber);
    exp = e;
  }

  public void accept(Visitor v) {
  }

  public Expression expression() {
    return exp;
  }
}
