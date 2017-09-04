/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.semanticactions;

import playerc.SemanticException;
import playerc.SemanticAction;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class SemanticActionFactory {
  public SemanticActionFactory() {
  }

  public static SemanticAction MakeAction(String symbol, int lineNumber) throws SemanticException {
    if (symbol.compareTo("MAKE-ARRAY-INITS-EXP") == 0)
      return new MakeArrayInitsExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-ARRAY-TYPE-DECL") == 0)
      return new MakeArrayTypeDeclaration(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-ASSIGNMENT-STATEMENT") == 0)
      return new MakeAssignmentStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-BINARY-OP-EXP") == 0)
      return new MakeBinaryOpExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-CALL-EXP") == 0)
      return new MakeCallExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-CALL-STATEMENT") == 0)
      return new MakeCallStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-DECLARATION-LIST") == 0)
      return new MakeDeclarationList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-DEREF-LVAL") == 0)
      return new MakeDerefLvalue(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-ELSE") == 0)
      return new MakeElseFragment(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-EXIT-STATEMENT") == 0)
      return new MakeExitStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-EXPRESSION-LIST") == 0)
      return new MakeExpressionList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-FALSE-EXP") == 0)
      return new MakeFalseExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-FOR-STATEMENT") == 0)
      return new MakeForStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-FORMAL-PARAMS") == 0)
      return new MakeFormalParams(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-FP-SECTION") == 0)
      return new MakeFPSection(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-FP-SECTION-LIST") == 0)
      return new MakeFPSectionList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-ID") == 0)
      return new MakeIdentifier(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-ID-LVAL") == 0)
      return new MakeIdLvalue(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-IF-STATEMENT") == 0)
      return new MakeIfStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-IFTHEN") == 0)
      return new MakeIfthenFragment(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-IFTHEN-LIST") == 0)
      return new MakeIfthenFragmentList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-INTEGER-EXP") == 0)
      return new MakeIntegerExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-LOOKUP-LVAL") == 0)
      return new MakeLookupLvalue(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-LOOP-STATEMENT") == 0)
      return new MakeLoopStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-LVAL-EXP") == 0)
      return new MakeLvalueExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-LVALUE-LIST") == 0)
      return new MakeLvalueList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-MARKER") == 0)
      return new MakeMarker(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-MEMBER") == 0)
      return new MakeMember(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-MEMBER-LIST") == 0)
      return new MakeMemberList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-NEW-TYPENAME") == 0)
      return new MakeNewTypename(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-NULL-EXP") == 0)
      return new MakeNullExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-OPERATOR") == 0)
      return new MakeOperator(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-PAREN-EXP") == 0)
      return new MakeParenExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-PRIMITIVE-TYPENAME") == 0)
      return new MakePrimitiveTypename(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-PROCEDURE-DECL") == 0)
      return new MakeProcedureDeclaration(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-PROGRAM") == 0)
      return new MakeProgram(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-READ-STATEMENT") == 0)
      return new MakeReadStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-REAL-EXP") == 0)
      return new MakeRealExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-RECORD-INIT") == 0)
      return new MakeRecordInit(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-RECORD-INIT-LIST") == 0)
      return new MakeRecordInitList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-RECORD-INITS-EXP") == 0)
      return new MakeRecordInitsExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-RECORD-TYPE-DECL") == 0)
      return new MakeRecordTypeDeclaration(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-RETURN-STATEMENT") == 0)
      return new MakeReturnStatement(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-STATEMENT-LIST") == 0)
      return new MakeStatementList(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-STRING-EXP") == 0)
      return new MakeStringExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-TRUE-EXP") == 0)
      return new MakeTrueExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-UNARY-OP-EXP") == 0)
      return new MakeUnaryOpExpression(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-VAR-DECL") == 0)
      return new MakeVarDeclaration(symbol, lineNumber);
    else if (symbol.compareTo("MAKE-WRITE-STATEMENT") == 0)
      return new MakeWriteStatement(symbol, lineNumber);
    else
      throw new SemanticException(symbol + " is not a semantic action", lineNumber);
  }
}
