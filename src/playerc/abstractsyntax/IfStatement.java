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
public class IfStatement extends Statement {
  private ExpThenStatementsList expStms;
  private StatementList elseStms;

  public IfStatement(ExpThenStatementsList expStms, StatementList elseStms, int lineNumber) {
    super(lineNumber);
    this.expStms = expStms;
    this.elseStms = elseStms;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public ExpThenStatementsList expStms() {
    return expStms;
  }

  public StatementList elseStms() {
    return elseStms;
  }
}
