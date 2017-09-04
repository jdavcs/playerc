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
public class WriteStatement extends Statement {
  private ExpressionList exps;

  public WriteStatement(ExpressionList exps, int lineNumber) {
    super(lineNumber);
    this.exps = exps;
  }

  public ExpressionList expressions() {
    return exps;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
