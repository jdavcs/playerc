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
public class IdExpression extends Expression {
  private Identifier id;

  public IdExpression(Identifier id, int lineNumber) {
    super(lineNumber);
    this.id = id;
  }

  public Identifier id() {
    return id;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
