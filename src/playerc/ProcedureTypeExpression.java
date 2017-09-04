package playerc;

import playerc.abstractsyntax.*;

/**
 * @author  Sergey Golitsynskiy
 * @version 3.1
 * created  Sep 05 2005
 * modified Sep 04 2017
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