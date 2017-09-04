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
public class RecordInit extends AbstractSyntaxTree {
  private Identifier id;
  private Expression exp;

  public RecordInit(Identifier id, Expression exp, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.exp = exp;
  }

  public Identifier id() {
    return id;
  }

  public Expression expression() {
    return exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
