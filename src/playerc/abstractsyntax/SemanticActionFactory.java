/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

import playerc.SemanticAction;
import playerc.SemanticException;
import playerc.semanticactions.MakeArrayInit;
import playerc.semanticactions.MakeArrayInitList;
import playerc.semanticactions.MakeArrayInitsExpression;
import playerc.semanticactions.MakeArrayType;
import playerc.semanticactions.MakeAssignmentStatement;
import playerc.semanticactions.MakeBinaryOpExpression;
import playerc.semanticactions.MakeBody;
import playerc.semanticactions.MakeBodyMarker;
import playerc.semanticactions.MakeByExpressionOpt;
import playerc.semanticactions.MakeCallExpression;
import playerc.semanticactions.MakeCallStatement;
import playerc.semanticactions.MakeDeclarationList;
import playerc.semanticactions.MakeDerefLvalue;
import playerc.semanticactions.MakeExitStatement;
import playerc.semanticactions.MakeExpThenStatements;
import playerc.semanticactions.MakeExpThenStatementsList;
import playerc.semanticactions.MakeExpressionList;
import playerc.semanticactions.MakeFalseExpression;
import playerc.semanticactions.MakeForStatement;
import playerc.semanticactions.MakeFormalParamsMarker;
import playerc.semanticactions.MakeFpSection;
import playerc.semanticactions.MakeFpSectionList;
import playerc.semanticactions.MakeId;
import playerc.semanticactions.MakeIdList;
import playerc.semanticactions.MakeIdLvalue;
import playerc.semanticactions.MakeIfStatement;
import playerc.semanticactions.MakeIntegerExpression;
import playerc.semanticactions.MakeLookupLvalue;
import playerc.semanticactions.MakeLoopMarker;
import playerc.semanticactions.MakeLoopStatement;
import playerc.semanticactions.MakeLvalueExpression;
import playerc.semanticactions.MakeLvalueList;
import playerc.semanticactions.MakeMember;
import playerc.semanticactions.MakeMemberList;
import playerc.semanticactions.MakeNewTypename;
import playerc.semanticactions.MakeNullExpression;
import playerc.semanticactions.MakeOperator;
import playerc.semanticactions.MakeParenExpression;
import playerc.semanticactions.MakePrimTypename;
import playerc.semanticactions.MakeProcDeclaration;
import playerc.semanticactions.MakeProgram;
import playerc.semanticactions.MakeReadMarker;
import playerc.semanticactions.MakeReadStatement;
import playerc.semanticactions.MakeRealExpression;
import playerc.semanticactions.MakeRecordInit;
import playerc.semanticactions.MakeRecordInitList;
import playerc.semanticactions.MakeRecordInitsExpression;
import playerc.semanticactions.MakeRecordType;
import playerc.semanticactions.MakeReturnMarker;
import playerc.semanticactions.MakeReturnStatement;
import playerc.semanticactions.MakeStatementList;
import playerc.semanticactions.MakeStringExpression;
import playerc.semanticactions.MakeTrueExpression;
import playerc.semanticactions.MakeTypeDeclaration;
import playerc.semanticactions.MakeUnaryOpExpression;
import playerc.semanticactions.MakeVarDeclaration;
import playerc.semanticactions.MakeWriteMarker;
import playerc.semanticactions.MakeWriteStatement;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class SemanticActionFactory {
  public SemanticActionFactory() {
  }

  public static SemanticAction MakeAction(String symbol, int lineNumber) throws SemanticException {
    if (symbol.compareTo("make-type-declaration") == 0)
      return new MakeTypeDeclaration(symbol, lineNumber);
    else if (symbol.compareTo("make-write-marker") == 0)
      return new MakeWriteMarker(symbol, lineNumber);
    else if (symbol.compareTo("make-var-declaration") == 0)
      return new MakeVarDeclaration(symbol, lineNumber);
    else if (symbol.compareTo("make-loop-marker") == 0)
      return new MakeLoopMarker(symbol, lineNumber);
    else if (symbol.compareTo("make-array-type") == 0)
      return new MakeArrayType(symbol, lineNumber);
    else if (symbol.compareTo("make-operator") == 0)
      return new MakeOperator(symbol, lineNumber);
    else if (symbol.compareTo("make-read-marker") == 0)
      return new MakeReadMarker(symbol, lineNumber);
    else if (symbol.compareTo("make-if-statement") == 0)
      return new MakeIfStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-call-statement") == 0)
      return new MakeCallStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-proc-declaration") == 0)
      return new MakeProcDeclaration(symbol, lineNumber);
    else if (symbol.compareTo("make-string-expression") == 0)
      return new MakeStringExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-array-inits-expression") == 0)
      return new MakeArrayInitsExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-deref-lvalue") == 0)
      return new MakeDerefLvalue(symbol, lineNumber);
    else if (symbol.compareTo("make-fp-section") == 0)
      return new MakeFpSection(symbol, lineNumber);
    else if (symbol.compareTo("make-prim-typename") == 0)
      return new MakePrimTypename(symbol, lineNumber);
    else if (symbol.compareTo("make-return-statement") == 0)
      return new MakeReturnStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-for-statement") == 0)
      return new MakeForStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-member-list") == 0)
      return new MakeMemberList(symbol, lineNumber);
    else if (symbol.compareTo("make-false-expression") == 0)
      return new MakeFalseExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-array-init") == 0)
      return new MakeArrayInit(symbol, lineNumber);
    else if (symbol.compareTo("make-write-statement") == 0)
      return new MakeWriteStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-unary-op-expression") == 0)
      return new MakeUnaryOpExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-statement-list") == 0)
      return new MakeStatementList(symbol, lineNumber);
    else if (symbol.compareTo("make-exit-statement") == 0)
      return new MakeExitStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-expression-list") == 0)
      return new MakeExpressionList(symbol, lineNumber);
    else if (symbol.compareTo("make-exp-then-statements-list") == 0)
      return new MakeExpThenStatementsList(symbol, lineNumber);
    else if (symbol.compareTo("make-new-typename") == 0)
      return new MakeNewTypename(symbol, lineNumber);
    else if (symbol.compareTo("make-id-list") == 0)
      return new MakeIdList(symbol, lineNumber);
    else if (symbol.compareTo("make-fp-section-list") == 0)
      return new MakeFpSectionList(symbol, lineNumber);
    else if (symbol.compareTo("make-array-init-list") == 0)
      return new MakeArrayInitList(symbol, lineNumber);
    else if (symbol.compareTo("make-assignment-statement") == 0)
      return new MakeAssignmentStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-by-expression-opt") == 0)
      return new MakeByExpressionOpt(symbol, lineNumber);
    else if (symbol.compareTo("make-exp-then-statements") == 0)
      return new MakeExpThenStatements(symbol, lineNumber);
    else if (symbol.compareTo("make-id") == 0)
      return new MakeId(symbol, lineNumber);
    else if (symbol.compareTo("make-return-marker") == 0)
      return new MakeReturnMarker(symbol, lineNumber);
    else if (symbol.compareTo("make-program") == 0)
      return new MakeProgram(symbol, lineNumber);
    else if (symbol.compareTo("make-lvalue-list") == 0)
      return new MakeLvalueList(symbol, lineNumber);
    else if (symbol.compareTo("make-binary-op-expression") == 0)
      return new MakeBinaryOpExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-declaration-list") == 0)
      return new MakeDeclarationList(symbol, lineNumber);
    else if (symbol.compareTo("make-lookup-lvalue") == 0)
      return new MakeLookupLvalue(symbol, lineNumber);
    else if (symbol.compareTo("make-loop-statement") == 0)
      return new MakeLoopStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-record-init-list") == 0)
      return new MakeRecordInitList(symbol, lineNumber);
    else if (symbol.compareTo("make-read-statement") == 0)
      return new MakeReadStatement(symbol, lineNumber);
    else if (symbol.compareTo("make-record-inits-expression") == 0)
      return new MakeRecordInitsExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-record-init") == 0)
      return new MakeRecordInit(symbol, lineNumber);
    else if (symbol.compareTo("make-real-expression") == 0)
      return new MakeRealExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-formal-params-marker") == 0)
      return new MakeFormalParamsMarker(symbol, lineNumber);
    else if (symbol.compareTo("make-record-type") == 0)
      return new MakeRecordType(symbol, lineNumber);
    else if (symbol.compareTo("make-call-expression") == 0)
      return new MakeCallExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-true-expression") == 0)
      return new MakeTrueExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-member") == 0)
      return new MakeMember(symbol, lineNumber);
    else if (symbol.compareTo("make-body") == 0)
      return new MakeBody(symbol, lineNumber);
    else if (symbol.compareTo("make-integer-expression") == 0)
      return new MakeIntegerExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-id-lvalue") == 0)
      return new MakeIdLvalue(symbol, lineNumber);
    else if (symbol.compareTo("make-lvalue-expression") == 0)
      return new MakeLvalueExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-body-marker") == 0)
      return new MakeBodyMarker(symbol, lineNumber);
    else if (symbol.compareTo("make-paren-expression") == 0)
      return new MakeParenExpression(symbol, lineNumber);
    else if (symbol.compareTo("make-null-expression") == 0)
      return new MakeNullExpression(symbol, lineNumber);
    else
      throw new SemanticException(symbol + " is not a semantic action", lineNumber);
  }
}
