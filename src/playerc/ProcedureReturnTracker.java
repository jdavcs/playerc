package playerc;

import playerc.abstractsyntax.*;
/**
 * Author: sergei
 * Created: Sep 3, 2005
 */
public class ProcedureReturnTracker
{/*
   private ProcDeclaration decl;
   private boolean hasReturn;
   
   public ProcedureReturnTracker(ProcDeclaration decl) 
   { 
      this.decl = decl; 
      hasReturn = false;
   }
   
   public void checkReturn(Expression exp) throws SemanticException
   {
      hasReturn = true;
      TypeExpression type = decl.type();
      if (exp == null)
      {
         if (!type.equals(TypeExpression.VoidType))
            throw new SemanticException("Procedure " + decl.id().toString() + 
                  " has an illegal return statement: expected return value of type " +
                  type.typename() +", found empty return statement", exp.lineNumber());      
      }
      else
      {
         if ( !type.equals(exp.type()) && 
               !(type.equals(TypeExpression.Real) && exp.type().equals(TypeExpression.Integer)) )
            throw new SemanticException("Procedure " + decl.id().toString() + 
                  " has an illegal return statement: expected return value of type " +
                  type.typename() +", found " + exp.type().typename(), exp.lineNumber());            
      }  
   }
   
   public boolean returnIsCorrect() 
   { 
      return decl.type().equals(TypeExpression.VoidType) || 
      	(!decl.type().equals(TypeExpression.VoidType) && hasReturn); 
   }*/
}
