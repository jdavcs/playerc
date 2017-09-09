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
public class VarDeclaration extends Declaration {
  private IdentifierList ids;
  private Typename typename;
  private Expression exp;

  public VarDeclaration(IdentifierList ids, Typename typename, Expression exp, int lineNumber) {
    super(lineNumber);
    this.ids = ids;
    this.typename = typename;
    this.exp = exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public IdentifierList ids() {
    return ids;
  }

  public Typename typename() {
    return typename;
  }

  public Expression expression() {
    return exp;
  }
}
