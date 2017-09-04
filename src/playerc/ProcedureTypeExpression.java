/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc;

import playerc.abstractsyntax.*;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class ProcedureTypeExpression extends TypeExpression {

  private TypeExpression returnType;
  private FPSectionList params;

  public ProcedureTypeExpression(TypeExpression returnType, FPSectionList params) {
    super(returnType.typename());
    this.returnType = returnType;
    this.params = params;
  }
  /*
   * public TypeExpression returnType() { return returnType; }
   * 
   * public FpSectionList formalParams() { return params; }
   */
}