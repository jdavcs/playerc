/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public interface Visitor {
  public void visit(Program x);

  public void visit(Body x);

  public void visit(VarDeclaration x);

  public void visit(ProcDeclaration x);

  public void visit(TypeDeclaration x);

  public void visit(PositiveExpression x);

  public void visit(NegativeExpression x);

  public void visit(NotExpression x);

  public void visit(PlusExpression x);

  public void visit(MinusExpression x);

  public void visit(MultExpression x);

  public void visit(DivExpression x);

  public void visit(AndExpression x);

  public void visit(OrExpression x);

  public void visit(GreaterExpression x);

  public void visit(GreaterEqlExpression x);

  public void visit(LessExpression x);

  public void visit(LessEqlExpression x);

  public void visit(EqlExpression x);

  public void visit(NotEqlExpression x);

  public void visit(TrueExpression x);

  public void visit(FalseExpression x);

  public void visit(IntegerExpression x);

  public void visit(RealExpression x);

  public void visit(StringExpression x);

  public void visit(ArrayInitsExpression x);

  public void visit(RecordInitsExpression x);

  public void visit(CallExpression x);

  public void visit(LookupExpression x);

  public void visit(DerefExpression x);

  public void visit(IdExpression x);

  public void visit(AssignmentStatement x);

  public void visit(CallStatement x);

  public void visit(ExitStatement x);

  public void visit(ForStatement x);

  public void visit(IfStatement x);

  public void visit(LoopStatement x);

  public void visit(ReadStatement x);

  public void visit(ReturnStatement x);

  public void visit(WriteStatement x);

  public void visit(DeclarationList x);

  public void visit(ExpressionList x);

  public void visit(StatementList x);

  public void visit(FpSectionList x);

  public void visit(IdentifierList x);

  public void visit(LvalueList x);

  public void visit(RecordType x);

  public void visit(NewTypename x);

  public void visit(PrimTypename x);

  public void visit(IdLvalue x);

  public void visit(LookupLvalue x);

  public void visit(DerefLvalue x);

  public void visit(FpSection x);

  public void visit(ParenExpression x);

  public void visit(NullExpression x);
}