/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

import playerc.TypeExpression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class Identifier extends AbstractSyntaxTree {
  private String value;
  private TypeExpression type;

  public Identifier(String value, int lineNumber) {
    super(lineNumber);
    this.value = value;
    type = null;
  }

  public void accept(Visitor v) {
  }

  public void setTypeExpression(TypeExpression t) {
    type = t;
  } // set by typechecker

  public String toString() {
    return value;
  }

  public TypeExpression type() {
    return type;
  }
}
