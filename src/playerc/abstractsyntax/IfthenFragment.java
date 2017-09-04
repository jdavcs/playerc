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
public class IfthenFragment extends AbstractSyntaxTree {
  private Expression exp;
  private StatementList stms;

  public IfthenFragment(Expression exp, StatementList stms, int lineNumber) {
    super(lineNumber);
    this.exp = exp;
    this.stms = stms;
  }

  public Expression expression() {
    return exp;
  }

  public StatementList statements() {
    return stms;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
