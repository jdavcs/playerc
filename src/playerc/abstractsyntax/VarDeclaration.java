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
public class VarDeclaration extends Declaration {
  private Identifier id;
  private Typename tn;
  private Expression exp;

  public VarDeclaration(Identifier id, Typename tn, Expression exp, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.tn = tn;
    this.exp = exp;
  }

  public Identifier id() {
    return id;
  }

  public Typename typename() {
    return tn;
  }

  public Expression expression() {
    return exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public String toString() {
    return "var decl";
  }
}
