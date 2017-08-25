package playerc;

import playerc.abstractsyntax.*;

/**
 * Author: sergei
 * Created: Sep 5, 2005
 */
public class ProcedureTypeExpression extends TypeExpression
{
	
   private TypeExpression returnType;
   private FPSectionList params;
   
   public ProcedureTypeExpression(TypeExpression returnType, FPSectionList params)
   {
      super(returnType.typename());
      this.returnType = returnType;
      this.params = params;
   }
   /*
   public TypeExpression returnType() { return returnType; }
   
   public FpSectionList formalParams() { return params; }*/
}