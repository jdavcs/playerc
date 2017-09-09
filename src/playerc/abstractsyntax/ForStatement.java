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
public class ForStatement extends Statement {
  private Identifier id;
  private Expression expFrom;
  private Expression expTo;
  private Expression expBy;
  private StatementList list;

  public ForStatement(Identifier id, Expression e1, Expression e2, Expression e3, StatementList stms, int lineNumber) {
    super(lineNumber);
    this.id = id;
    expFrom = e1;
    expTo = e2;
    expBy = e3;
    list = stms;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Identifier id() {
    return id;
  }

  public Expression expFrom() {
    return expFrom;
  }

  public Expression expTo() {
    return expTo;
  }

  public Expression expBy() {
    return expBy;
  }

  public StatementList list() {
    return list;
  }
}
