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
public class ProcDeclaration extends Declaration {
  private Identifier id;
  private FpSectionList params;
  private Typename typename;
  private Body body;
  private TypeExpression type;

  public ProcDeclaration(Identifier id, FpSectionList params, Typename typename, Body body, int lineNumber) {
    super(lineNumber);
    this.id = id;
    this.params = params;
    this.typename = typename;
    this.body = body;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Identifier id() {
    return id;
  }

  public FpSectionList params() {
    return params;
  }

  public Typename typename() {
    return typename;
  }

  public Body body() {
    return body;
  }

  public TypeExpression type() {
    return type;
  }

  public void setType(TypeExpression type) {
    this.type = type;
  }
}