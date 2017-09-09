
/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import playerc.abstractsyntax.AbstractSyntaxTree;
import playerc.abstractsyntax.AndExpression;
import playerc.abstractsyntax.ArrayInit;
import playerc.abstractsyntax.ArrayInitsExpression;
import playerc.abstractsyntax.ArrayType;
import playerc.abstractsyntax.AssignmentStatement;
import playerc.abstractsyntax.BinaryOpExpression;
import playerc.abstractsyntax.Body;
import playerc.abstractsyntax.CallExpression;
import playerc.abstractsyntax.CallStatement;
import playerc.abstractsyntax.Declaration;
import playerc.abstractsyntax.DeclarationList;
import playerc.abstractsyntax.DerefExpression;
import playerc.abstractsyntax.DerefLvalue;
import playerc.abstractsyntax.DivExpression;
import playerc.abstractsyntax.EqlExpression;
import playerc.abstractsyntax.ExitStatement;
import playerc.abstractsyntax.ExpThenStatements;
import playerc.abstractsyntax.ExpThenStatementsList;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.ExpressionList;
import playerc.abstractsyntax.FalseExpression;
import playerc.abstractsyntax.ForStatement;
import playerc.abstractsyntax.FpSection;
import playerc.abstractsyntax.FpSectionList;
import playerc.abstractsyntax.GreaterEqlExpression;
import playerc.abstractsyntax.GreaterExpression;
import playerc.abstractsyntax.IdExpression;
import playerc.abstractsyntax.IdLvalue;
import playerc.abstractsyntax.Identifier;
import playerc.abstractsyntax.IdentifierList;
import playerc.abstractsyntax.IfStatement;
import playerc.abstractsyntax.IntegerExpression;
import playerc.abstractsyntax.LessEqlExpression;
import playerc.abstractsyntax.LessExpression;
import playerc.abstractsyntax.LookupExpression;
import playerc.abstractsyntax.LookupLvalue;
import playerc.abstractsyntax.LoopStatement;
import playerc.abstractsyntax.Lvalue;
import playerc.abstractsyntax.LvalueList;
import playerc.abstractsyntax.Member;
import playerc.abstractsyntax.MinusExpression;
import playerc.abstractsyntax.MultExpression;
import playerc.abstractsyntax.NegativeExpression;
import playerc.abstractsyntax.NewType;
import playerc.abstractsyntax.NewTypename;
import playerc.abstractsyntax.NotEqlExpression;
import playerc.abstractsyntax.NotExpression;
import playerc.abstractsyntax.NullExpression;
import playerc.abstractsyntax.OrExpression;
import playerc.abstractsyntax.ParenExpression;
import playerc.abstractsyntax.PlusExpression;
import playerc.abstractsyntax.PositiveExpression;
import playerc.abstractsyntax.PrimTypename;
import playerc.abstractsyntax.ProcDeclaration;
import playerc.abstractsyntax.Program;
import playerc.abstractsyntax.ReadStatement;
import playerc.abstractsyntax.RealExpression;
import playerc.abstractsyntax.RecordInit;
import playerc.abstractsyntax.RecordInitsExpression;
import playerc.abstractsyntax.RecordType;
import playerc.abstractsyntax.ReturnStatement;
import playerc.abstractsyntax.Statement;
import playerc.abstractsyntax.StatementList;
import playerc.abstractsyntax.StringExpression;
import playerc.abstractsyntax.TrueExpression;
import playerc.abstractsyntax.TypeDeclaration;
import playerc.abstractsyntax.Typename;
import playerc.abstractsyntax.Value;
import playerc.abstractsyntax.VarDeclaration;
import playerc.abstractsyntax.Visitor;
import playerc.abstractsyntax.WriteStatement;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class TypeChecker implements Visitor {
  private SymbolTable symbols;
  private RecordSymbolTables recordSymbols;
  private int foundErrors; // semantic & class cast error counter
  private Stack procStack; // keeps track of current procedure; used to check
                           // return statement
  private int activeLoops;

  public TypeChecker() {
    initSymbols();
    foundErrors = 0;
    procStack = new Stack();
    activeLoops = 0;
  }

  protected SymbolTable symbols() {
    return symbols;
  }

  public int foundErrors() {
    return foundErrors;
  }

  public void check(Program p) {
    p.accept(this);
  }

  public void visit(Program x) {
    Identifier name = x.name();

    try {
      symbols.insert(name.toString(), TypeExpression.Program, name.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }

    x.name().accept(this);
    x.body().accept(this);
  }

  public void visit(Body x) {
    // pass #1: process all type names: add records to symbol table with type =
    // null
    Iterator decls1 = x.decls().iterator();
    while (decls1.hasNext()) {
      Declaration d = (Declaration) decls1.next();
      if (d instanceof TypeDeclaration)
        initTypes((TypeDeclaration) d);
      else if (d instanceof VarDeclaration)
        initVariables((VarDeclaration) d);
      else if (d instanceof ProcDeclaration)
        initProcedures((ProcDeclaration) d);
    }
    // pass #2: typecheck declarations
    Iterator decls2 = x.decls().iterator();
    while (decls2.hasNext())
      ((Declaration) decls2.next()).accept(this);

    Iterator stms = x.stms().iterator();
    while (stms.hasNext())
      ((Statement) stms.next()).accept(this);
  }

  private void initTypes(TypeDeclaration d) {
    try {
      symbols.insert(d.typename().toString(), TypeExpression.TemporaryType, d.typename().lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  private void initVariables(VarDeclaration d) {
    // store all ids with temporary type in symboltable
    Iterator ids = d.ids().iterator();
    while (ids.hasNext()) {
      Identifier id = (Identifier) ids.next();
      try {
        symbols.insert(id.toString(), TypeExpression.TemporaryType, id.lineNumber());
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
      }
    }
  }

  private void initProcedures(ProcDeclaration d) {
    Identifier id = d.id();
    Typename typename = d.typename();

    if (typename != null)
      try {
        TypeExpression t = TypeExpression.makeTypeExpression(typename.toString());
        symbols.insert(id.toString(), t, id.lineNumber());
        d.setType(t);
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
        d.setType(TypeExpression.ErrorType);
      }
    else
      try {
        TypeExpression t = TypeExpression.VoidType;
        symbols.insert(id.toString(), t, id.lineNumber());
        d.setType(t);
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
        d.setType(TypeExpression.ErrorType);
      }

    symbols.setScope();
    FpSectionList params = d.params();
    if (params != null)
      params.accept(this);
    symbols.removeScope();

    ProcedureTypeExpression typeExp = new ProcedureTypeExpression(d.type(), params);
    // update symbols with new proctypeexpression boubnd to this id!!!
    try {
      symbols.update(d.id().toString(), typeExp, d.id().lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  /*----------------------DECLARATIONS------------------------*/
  public void visit(TypeDeclaration x) {
    NewType type = x.type();
    String typename = x.typename().toString();
    TypeExpression typeExp = null;

    if (type instanceof ArrayType)
      typeExp = buildArrayType((ArrayType) type, typename);
    else if (type instanceof RecordType)
      typeExp = buildRecordType((RecordType) type, typename);

    // update typename of this record with TypeExpression in symboltable
    try {
      symbols.update(typename, typeExp, x.typename().lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  private TypeExpression buildArrayType(ArrayType type, String typename) {
    checkTypename(type.elementTypename());
    Typename elementTypename = type.elementTypename();
    TypeExpression elementType = TypeExpression.makeTypeExpression(elementTypename.toString());
    return new ArrayTypeExpression(typename, elementType);
  }

  private TypeExpression buildRecordType(RecordType type, String typename) {
    HashMap memberRecords = new HashMap();
    Iterator i = type.members().iterator();

    while (i.hasNext()) {
      Member m = (Member) i.next();
      String mId = m.id().toString();
      String mTypename = m.typename().toString();

      TypeExpression mTypeExp;
      try {
        mTypeExp = symbols.lookup(mTypename, m.lineNumber());
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
        mTypeExp = TypeExpression.ErrorType;
      }
      memberRecords.put(mId, mTypeExp);

      try {
        recordSymbols.insert(typename, mId, mTypeExp, m.id().lineNumber());
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
      }
      m.id().setTypeExpression(mTypeExp); // used in code gen
    }
    return new RecordTypeExpression(typename, memberRecords);
  }

  public void visit(RecordType x) {
    Iterator i = x.members().iterator();
    while (i.hasNext()) {
      Member m = (Member) i.next();
      try {
        symbols.lookup(m.typename().toString(), m.lineNumber());
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString() + " #1");
      }
    }
  }

  public void visit(NewTypename x) {
    checkTypename(x);
  }

  public void visit(PrimTypename x) {
    checkTypename(x);
  }

  public void visit(VarDeclaration x) {
    // get TypeExpression
    TypeExpression typeExp = null;
    Typename typename = x.typename();
    Expression expression = x.expression();

    if (typename != null) // if the type IS explicitly stated
    {
      typeExp = TypeExpression.makeTypeExpression(typename.toString());
      expression.accept(this);

      if (typeExp.equals(TypeExpression.Real)) // then integer will do
        try {
          if (!expression.type().equals(TypeExpression.Real) && !expression.type().equals(TypeExpression.Integer))
            throwException(TypeExpression.Real.toString(), expression.type().typename(), expression.lineNumber());
        } catch (SemanticException e) {
          foundErrors++;
          System.err.println(e.toString());
        }
      else if (!expression.type().equals(TypeExpression.NullType))
        checkValueType(expression, typeExp); // explicitly declared type must be
                                             // the same as expression type
                                             // (except int/real)
    } else // if NOT - derive from expression
    {
      expression.accept(this);
      typeExp = expression.type();
    }

    // update all ids with TypeExpression in symboltable
    Iterator ids = x.ids().iterator();
    while (ids.hasNext()) {
      Identifier id = (Identifier) ids.next();
      try {
        symbols.update(id.toString(), typeExp, id.lineNumber());
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
      }
      id.setTypeExpression(typeExp);
    }
  }

  public void visit(ProcDeclaration x) {
    procStack.push(new ProcedureReturnTracker(x));

    symbols.setScope();

    // adding formal params to scope (processing second time)
    FpSectionList params = x.params();
    if (params != null)
      params.accept(this);

    Body body = x.body();
    body.accept(this); // must find return statement
    symbols.removeScope();

    ProcedureReturnTracker tracker = (ProcedureReturnTracker) procStack.pop();
    try {
      if (!tracker.returnIsCorrect())
        throw new SemanticException("Procedure " + x.id().toString() + " is missing a return statement",
            body.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }

    try {
      TypeExpression te = symbols.lookup(x.type().typename(), x.lineNumber());
      x.setType(te);
    } catch (SemanticException e) {
      System.err.println(e.toString());
    }
  }

  public void visit(FpSection x) {
    try {
      String typename = x.typename().toString();
      TypeExpression typeExp = symbols.lookup(typename, x.lineNumber());
      Identifier id = x.id();
      symbols.insert(id.toString(), typeExp, id.lineNumber());
      x.setTypeExpression(typeExp);
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  /*----------------------EXPRESSIONS------------------------*/
  public void visit(TrueExpression x) {
    x.setType(TypeExpression.Boolean);
  }

  public void visit(FalseExpression x) {
    x.setType(TypeExpression.Boolean);
  }

  public void visit(IntegerExpression x) {
    x.setType(TypeExpression.Integer);
  }

  public void visit(RealExpression x) {
    x.setType(TypeExpression.Real);
  }

  public void visit(StringExpression x) {
    x.setType(TypeExpression.StringType);
  }

  public void visit(AndExpression x) {
    runBooleanBinaryOpVisit(x);
  }

  public void visit(OrExpression x) {
    runBooleanBinaryOpVisit(x);
  }

  public void visit(NotExpression x) {
    Expression expression = x.exp();
    expression.accept(this);
    checkValueType(expression, TypeExpression.Boolean);

    x.setType(TypeExpression.Boolean);
  }

  public void visit(PositiveExpression x) {
    Expression expression = x.exp();
    expression.accept(this);
    checkNumericValue(expression);

    x.setType(expression.type());
  }

  public void visit(NegativeExpression x) {
    Expression expression = x.exp();
    expression.accept(this);
    checkNumericValue(expression);

    x.setType(expression.type());
  }

  public void visit(PlusExpression x) {
    runNumericBinaryOpVisit(x);
  }

  public void visit(MinusExpression x) {
    runNumericBinaryOpVisit(x);
  }

  public void visit(MultExpression x) {
    runNumericBinaryOpVisit(x);
  }

  public void visit(DivExpression x) {
    runNumericBinaryOpVisit(x);
  }

  public void visit(GreaterExpression x) {
    runRelationalBinaryOpVisit(x);
  }

  public void visit(GreaterEqlExpression x) {
    runRelationalBinaryOpVisit(x);
  }

  public void visit(LessExpression x) {
    runRelationalBinaryOpVisit(x);
  }

  public void visit(LessEqlExpression x) {
    runRelationalBinaryOpVisit(x);
  }

  public void visit(EqlExpression x) {
    runRelationalBinaryOpVisit(x);
  }

  public void visit(NotEqlExpression x) {
    runRelationalBinaryOpVisit(x);
  }

  public void visit(IdExpression x) {
    Identifier id = x.id();
    TypeExpression dt = null;
    try {
      dt = symbols.lookup(id.toString(), id.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
      dt = TypeExpression.ErrorType;
    }
    x.setType(dt);
  }

  public void visit(ParenExpression x) {
    x.exp().accept(this);
    x.setType(x.exp().type());
  }

  public void visit(NullExpression x) {
    x.setType(TypeExpression.NullType);
  }

  public void visit(ArrayInitsExpression x) {
    try {
      ArrayTypeExpression arrType = (ArrayTypeExpression) symbols.lookup(x.typename().toString(), x.lineNumber());
      TypeExpression elementType = arrType.elementType();

      Iterator arrInits = x.list().iterator();
      while (arrInits.hasNext()) {
        ArrayInit ai = (ArrayInit) arrInits.next();

        IntegerExpression numberOf = ai.numberOf(); // no need to check - it's
                                                    // an integerexpression
        // the lang description needs more details: according to the grammar, I
        // could use an id as numberOf,
        // but an id could be determined at runtime only - and that's not
        // possible in java, i think.
        numberOf.accept(this);

        Expression expression = ai.expression();
        expression.accept(this);
        checkValueType(expression, elementType);
      }
      x.setType(arrType);
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    } catch (ClassCastException e) {
      foundErrors++;
      System.err.println(e.toString() + " - expected ArrayTypeExpression");
    }
  }

  public void visit(RecordInitsExpression x) {
    try {
      RecordTypeExpression recType = (RecordTypeExpression) symbols.lookup(x.typename().toString(), x.lineNumber());
      Iterator inits = x.list().iterator();

      while (inits.hasNext()) {
        RecordInit init = (RecordInit) inits.next();

        Identifier id = init.id();
        try {
          TypeExpression memberType = recType.memberType(id.toString(), init.lineNumber());

          Expression expression = init.expression();
          expression.accept(this);
          checkValueType(expression, memberType);
        } catch (SemanticException e) {
          foundErrors++;
          System.err.println(e.toString());
        }
      }
      x.setType(recType);
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    } catch (ClassCastException e) {
      foundErrors++;
      System.err.println(e.toString() + " - expected RecordTypeExpression");
    }
  }

  public void visit(LookupExpression x) {
    Lvalue lvalue = x.lvalue();
    lvalue.accept(this);

    Expression expression = x.expression();
    expression.accept(this);
    checkValueType(expression, TypeExpression.Integer); // array index must be
                                                        // an int

    try {
      ArrayTypeExpression typeExp = (ArrayTypeExpression) symbols.lookup(lvalue.type().typename(), x.lineNumber());
      lvalue.setType(typeExp);
      x.setType(typeExp.elementType());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString() + " - expected ArrayTypeExpression");
      x.setType(TypeExpression.ErrorType);
    }
  }

  public void visit(DerefExpression x) {
    Lvalue lvalue = x.lvalue();
    lvalue.accept(this);

    Identifier id = x.id();

    RecordTypeExpression recType = null;
    TypeExpression memberType = null;
    try {
      if (!(lvalue.type() instanceof RecordTypeExpression))
        throwException("any RecordTypeExpression", lvalue.type().typename(), lvalue.lineNumber());

      recType = (RecordTypeExpression) lvalue.type();
      memberType = recType.memberType(id.toString(), id.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      memberType = TypeExpression.ErrorType;
      System.err.println(e.toString());
    }
    x.setType(memberType);
  }

  public void visit(IdLvalue x) {
    Identifier id = x.id();
    TypeExpression type = null;
    try {
      type = symbols.lookup(id.toString(), id.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
      type = TypeExpression.ErrorType; // but will it retrieve an arraysatatype?
    }
    x.setType(type);
  }

  public void visit(LookupLvalue x) {
    Lvalue lvalue = x.lvalue();
    lvalue.accept(this);

    Expression expression = x.expression();
    expression.accept(this);
    checkValueType(expression, TypeExpression.Integer); // array index must be
                                                        // an int

    try {
      ArrayTypeExpression type = (ArrayTypeExpression) lvalue.type();
      x.setType(type.elementType());
    } catch (ClassCastException e) {
      foundErrors++;
      System.err.println(e.toString() + " - expected ArrayTypeExpression");
      x.setType(TypeExpression.ErrorType);
    }
  }

  public void visit(DerefLvalue x) {
    Lvalue lvalue = x.lvalue();
    lvalue.accept(this);

    Identifier id = x.id();

    RecordTypeExpression recType = null;
    TypeExpression memberType = null;
    try {
      if (!(lvalue.type() instanceof RecordTypeExpression))
        throwException("any RecordTypeExpression", lvalue.type().typename(), lvalue.lineNumber());

      recType = (RecordTypeExpression) lvalue.type();
      memberType = recType.memberType(id.toString(), id.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      memberType = TypeExpression.ErrorType;
      System.err.println(e.toString());
    }
    x.setType(memberType);
  }

  public void visit(CallExpression x) {
    checkCallConstruct(x, x.id(), x.params());
  }

  private void checkCallConstruct(AbstractSyntaxTree construct, Identifier id, ExpressionList actualParams) {
    ProcedureTypeExpression type = null;
    try {
      type = (ProcedureTypeExpression) symbols.lookup(id.toString(), id.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
    if (construct instanceof Value)
      ((Value) construct).setType(type);

    if (actualParams != null)
      actualParams.accept(this);

    FpSectionList formalParams = type.formalParams();
    try {
      if ((actualParams == null && formalParams != null) || (actualParams != null && formalParams == null))
        throw new SemanticException("procedure " + id.toString() + ": wrong actual parameters", id.lineNumber());

      if (!(actualParams == null && formalParams == null)) {
        if (actualParams.size() != formalParams.size())
          throw new SemanticException("procedure " + id.toString() + ": wrong actual parameters", id.lineNumber());
        checkActualParams(type.formalParams(), actualParams, id.lineNumber());
      }
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  private void checkActualParams(FpSectionList formalParams, ExpressionList actualParams, int line)
      throws SemanticException {
    Iterator iF = formalParams.iterator();
    Iterator iA = actualParams.iterator();
    while (iF.hasNext()) {
      FpSection fParam = (FpSection) iF.next();
      String expectedTypename = fParam.typename().toString();

      Expression exp = (Expression) iA.next();
      String foundTypename = exp.type().typename();

      if ((expectedTypename.compareTo(foundTypename) != 0)
          && !(expectedTypename.compareTo("real") == 0 && foundTypename.compareTo("integer") == 0))
        throwException(expectedTypename, foundTypename, line);
    }
  }

  /*----------------------STATEMENTS------------------------*/
  public void visit(AssignmentStatement x) {
    Lvalue lvalue = x.lvalue();
    lvalue.accept(this);

    Expression exp = x.expression();
    exp.accept(this);
    checkValueType(exp, lvalue.type());
  }

  public void visit(LoopStatement x) {
    activeLoops++;
    StatementList stms = x.list();
    stms.accept(this);
    activeLoops--;
  }

  public void visit(WriteStatement x) {
    ExpressionList exps = x.expressionList();
    exps.accept(this);
  }

  public void visit(ForStatement x) {
    activeLoops++;
    symbols.setScope();

    Identifier id = x.id();
    try {
      symbols.insert(id.toString(), TypeExpression.Integer, id.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }

    Expression expFrom = x.expFrom();
    expFrom.accept(this);
    checkValueType(expFrom, TypeExpression.Integer);

    Expression expTo = x.expTo();
    expTo.accept(this);
    checkValueType(expTo, TypeExpression.Integer);

    Expression expBy = x.expBy();
    expBy.accept(this);
    checkValueType(expBy, TypeExpression.Integer);

    StatementList list = x.list();
    list.accept(this);

    symbols.removeScope();
    activeLoops--;
  }

  public void visit(IfStatement x) {
    ExpThenStatementsList expStmsList = x.expStms();
    Iterator i = expStmsList.iterator();
    while (i.hasNext()) {
      ExpThenStatements expStms = (ExpThenStatements) i.next();

      Expression exp = expStms.expression();
      exp.accept(this);

      checkValueType(exp, TypeExpression.Boolean);

      StatementList stms = expStms.statements();
      stms.accept(this);
    }
    StatementList elseStm = x.elseStms();
    if (elseStm != null)
      elseStm.accept(this);
  }

  public void visit(ExitStatement x) {
    try {
      if (activeLoops < 1)
        throw new SemanticException("illegal exit statement", x.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  public void visit(ReadStatement x) {
    LvalueList lvals = x.lvalueList();
    Iterator i = lvals.iterator();
    while (i.hasNext()) {
      Lvalue lval = (Lvalue) i.next();
      lval.accept(this);

      try {
        if (!(isString(lval) || isNumeric(lval)))
          throwException("numeric or string", lval.type().typename(), lval.lineNumber());
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
      }
    }
  }

  public void visit(ReturnStatement x) {
    Expression exp = x.expression();
    if (exp != null)
      exp.accept(this);

    try {
      if (procStack.empty())
        throw new SemanticException("illegal return statement in main body", x.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }

    if (!procStack.empty()) {
      ProcedureReturnTracker tracker = (ProcedureReturnTracker) procStack.peek();
      try {
        tracker.checkReturn(exp);
      } catch (SemanticException e) {
        foundErrors++;
        System.err.println(e.toString());
      }
    }
  }

  public void visit(CallStatement x) {
    checkCallConstruct(x, x.id(), x.params());
  }

  public void visit(DeclarationList x) {
    Iterator i = x.iterator();
    while (i.hasNext())
      ((Declaration) i.next()).accept(this);
  }

  public void visit(ExpressionList x) {
    Iterator i = x.iterator();
    while (i.hasNext())
      ((Expression) i.next()).accept(this);
  }

  public void visit(StatementList x) {
    Iterator i = x.iterator();
    while (i.hasNext())
      ((Statement) i.next()).accept(this);
  }

  public void visit(FpSectionList x) {
    Iterator i = x.iterator();
    while (i.hasNext())
      ((FpSection) i.next()).accept(this);
  }

  public void visit(IdentifierList x) {
    Iterator i = x.iterator();
    while (i.hasNext())
      ((Identifier) i.next()).accept(this);
  }

  public void visit(LvalueList x) {
    Iterator i = x.iterator();
    while (i.hasNext())
      ((Lvalue) i.next()).accept(this);
  }

  /*----------------------HELPERS-----------------------------*/
  private boolean areComparable(Expression e1, Expression e2) {
    if (isNumeric(e1) && isNumeric(e2))
      return true; // true if both are numeric

    String type1 = e1.type().typename();
    String type2 = e2.type().typename();

    if (type1.compareTo(TypeExpression.StringType.typename()) == 0)
      return false; // can't compare strings
    else
      return (type1.compareTo(type2) == 0); // else - must be the same
  }

  private boolean isNumeric(Value e) {
    return (e.type().equals(TypeExpression.Integer) || e.type().equals(TypeExpression.Real));
  }

  private boolean isBoolean(Value e) {
    return (e.type().equals(TypeExpression.Boolean));
  }

  private boolean isInteger(Value e) {
    return (e.type().equals(TypeExpression.Integer));
  }

  private boolean isReal(Value e) {
    return (e.type().equals(TypeExpression.Real));
  }

  private boolean isString(Value e) {
    return (e.type().equals(TypeExpression.StringType));
  }

  private void runNumericBinaryOpVisit(BinaryOpExpression x) {
    Expression left = x.left();
    left.accept(this);
    checkNumericValue(left);

    Expression right = x.right();
    right.accept(this);
    checkNumericValue(right);

    x.setType(generalize(left, right));
  }

  private void runRelationalBinaryOpVisit(BinaryOpExpression x) {
    Expression left = x.left();
    left.accept(this);

    Expression right = x.right();
    right.accept(this);

    try {
      if (!areNumeric(left, right) && !(areOfSameType(left, right)
          && (left.type() instanceof ArrayTypeExpression || left.type() instanceof RecordTypeExpression)))
        throw new SemanticException("expected a pair of numeric or array/record of same type expressions, found "
            + left.type().typename() + " and " + right.type().typename() + " expressions", x.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
    x.setType(TypeExpression.Boolean);
  }

  private boolean areNumeric(Expression e1, Expression e2) {
    return isNumeric(e1) && isNumeric(e2);
  }

  private boolean areOfSameType(Expression e1, Expression e2) {
    return e1.type().equals(e2.type());
  }

  private void runBooleanBinaryOpVisit(BinaryOpExpression x) {
    Expression left = x.left();
    left.accept(this);
    checkValueType(left, TypeExpression.Boolean);

    Expression right = x.right();
    right.accept(this);
    checkValueType(right, TypeExpression.Boolean);

    x.setType(TypeExpression.Boolean);
  }

  private TypeExpression generalize(Value left, Value right) {
    if (left.type().equals(TypeExpression.Real) || right.type().equals(TypeExpression.Real))
      return TypeExpression.Real;
    else
      return TypeExpression.Integer;
  }

  private void checkValueType(Value val, TypeExpression type) {
    try {
      if (!val.type().equals(type)
          && !((val.type().equals(TypeExpression.Integer)) && type.equals(TypeExpression.Real)))
        throwException(type.typename(), val.type().typename(), val.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  private void checkNumericValue(Value val) {
    try {
      if (!isNumeric(val))
        throwException("integer or real", val.type().typename(), val.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  private void throwException(String expected, String found, int line) throws SemanticException {
    throw new SemanticException("expected " + expected + " expression, found " + found + " expression", line);
  }

  private void initSymbols() {
    recordSymbols = new RecordSymbolTables();
    symbols = new SymbolTable();
    try {
      symbols.insert("boolean", TypeExpression.Boolean, -1);
      symbols.insert("integer", TypeExpression.Integer, -1);
      symbols.insert("real", TypeExpression.Real, -1);
      symbols.insert("string", TypeExpression.StringType, -1);
      symbols.insert("void", TypeExpression.VoidType, -1);
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  private void checkTypename(Typename x) {
    try {
      symbols.lookup(x.toString(), x.lineNumber());
    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }
  }

  private void checkComparable(BinaryOpExpression exp) {
    Expression left = exp.left();
    left.accept(this);

    Expression right = exp.right();
    right.accept(this);

    try {
      if (!areComparable(left, right))
        throwException("integers/reals or booleans or arrays or records ",
            left.type().typename() + " and " + right.type().typename(), exp.lineNumber());

    } catch (SemanticException e) {
      foundErrors++;
      System.err.println(e.toString());
    }

    exp.setType(TypeExpression.Boolean);
  }
}
