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
public abstract class Value extends AbstractSyntaxTree {
  private TypeExpression type;

  public Value(int lineNumber) {
    super(lineNumber);
  }

  public TypeExpression type() {
    return type;
  }

  public void setType(TypeExpression type) {
    this.type = type;
  }
}
