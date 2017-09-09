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
public class StringExpression extends Expression {
  String value;

  public StringExpression(String s, int lineNumber) {
    super(lineNumber);
    value = s;
  }

  public String value() {
    return value;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
