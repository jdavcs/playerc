/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

import playerc.TypeExpression;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class FpSection extends AbstractSyntaxTree {
  private Identifier id;
  private Typename typename;
  private TypeExpression type;

  public FpSection(Identifier id, Typename typename, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.typename = typename;
    type = null;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Identifier id() {
    return id;
  }

  public Typename typename() {
    return typename;
  }

  public void setTypeExpression(TypeExpression t) {
    type = t;
  } // set by typechecker

  public TypeExpression type() {
    return type;
  }
}
