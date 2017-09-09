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
public class Body extends AbstractSyntaxTree {
  private DeclarationList decls;
  private StatementList stms;

  public Body(DeclarationList decls, StatementList stms, int lineNumber) {
    super(lineNumber);
    this.decls = decls;
    this.stms = stms;
  }

  public DeclarationList decls() {
    return decls;
  }

  public StatementList stms() {
    return stms;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
