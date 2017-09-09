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
public class WriteStatement extends Statement {
  private ExpressionList expList;

  public WriteStatement(ExpressionList list, int lineNumber) {
    super(lineNumber);
    expList = list;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public ExpressionList expressionList() {
    return expList;
  }
}