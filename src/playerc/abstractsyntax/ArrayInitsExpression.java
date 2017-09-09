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
public class ArrayInitsExpression extends Expression {
  private Typename typename;
  private ArrayInitList list;

  public ArrayInitsExpression(Typename typename, ArrayInitList list, int lineNumber) {
    super(lineNumber);
    this.typename = typename;
    this.list = list;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Typename typename() {
    return typename;
  }

  public ArrayInitList list() {
    return list;
  }
}
