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
public class NegativeExpression extends UnaryOpExpression {
  public NegativeExpression(Expression exp, int lineNumber) {
    super(exp, lineNumber);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
