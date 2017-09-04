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
public class ReadStatement extends Statement {
  private LvalueList lvals;

  public ReadStatement(LvalueList lvals, int lineNumber) {
    super(lineNumber);
    this.lvals = lvals;
  }

  public LvalueList lvalues() {
    return lvals;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
