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
public class ArrayInit extends AbstractSyntaxTree {
  private IntegerExpression numberOf;
  private Expression expression;

  public ArrayInit(IntegerExpression numberOf, Expression expression, int lineNumber) {
    super(lineNumber);
    this.numberOf = numberOf;
    this.expression = expression;
  }

  public void accept(Visitor v) {
  }

  public IntegerExpression numberOf() {
    return numberOf;
  }

  public Expression expression() {
    return expression;
  }
}
