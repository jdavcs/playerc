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
public class ExpThenStatements extends AbstractSyntaxTree {
  private Expression expression;
  private StatementList statements;

  public ExpThenStatements(Expression expression, StatementList statements, int lineNumber) {
    super(lineNumber);
    this.expression = expression;
    this.statements = statements;
  }

  public void accept(Visitor v) {
  }

  public Expression expression() {
    return expression;
  }

  public StatementList statements() {
    return statements;
  }
}
