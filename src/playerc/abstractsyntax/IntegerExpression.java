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
public class IntegerExpression extends Expression {
  private int value;

  public IntegerExpression(int value, int lineNumber) {
    super(lineNumber);
    this.value = value;
  }

  public int value() {
    return value;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
