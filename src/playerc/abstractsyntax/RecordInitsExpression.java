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
public class RecordInitsExpression extends Expression {
  private Identifier id;
  private RecordInitList inits;

  public RecordInitsExpression(Identifier id, RecordInitList inits, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.inits = inits;
  }

  public Identifier id() {
    return id;
  }

  public RecordInitList recordInits() {
    return inits;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
