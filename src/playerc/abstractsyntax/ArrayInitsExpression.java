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
public class ArrayInitsExpression extends Expression {
  private Identifier id;
  private ExpressionList list;

  public ArrayInitsExpression(Identifier id, ExpressionList list, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.list = list;
  }

  public Identifier id() {
    return id;
  }

  public ExpressionList expressions() {
    return list;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
