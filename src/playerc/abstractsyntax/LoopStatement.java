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
public class LoopStatement extends Statement {
  private StatementList stms;

  public LoopStatement(StatementList stms, int lineNumber) {
    super(lineNumber);
    this.stms = stms;
  }

  public StatementList statements() {
    return stms;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
