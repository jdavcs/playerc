/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */

public class ArrayTypeExpression extends TypeExpression {
  private TypeExpression elementType;

  public ArrayTypeExpression(String typename, TypeExpression elementType) {
    super(typename);
    this.elementType = elementType;
  }

  public TypeExpression elementType() {
    return elementType;
  }
}
