/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import playerc.abstractsyntax.FpSectionList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class ProcedureTypeExpression extends TypeExpression {
  private TypeExpression returnType;
  private FpSectionList params;

  public ProcedureTypeExpression(TypeExpression returnType, FpSectionList params) {
    super(returnType.typename());
    this.returnType = returnType;
    this.params = params;
  }

  public TypeExpression returnType() {
    return returnType;
  }

  public FpSectionList formalParams() {
    return params;
  }
}