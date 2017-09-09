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
public class LoopStatement extends Statement {
  private StatementList list;

  public LoopStatement(StatementList list, int lineNumber) {
    super(lineNumber);
    this.list = list;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public StatementList list() {
    return list;
  }
}
