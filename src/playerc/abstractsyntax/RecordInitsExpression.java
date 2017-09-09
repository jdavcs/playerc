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
public class RecordInitsExpression extends Expression {
  private NewTypename typename;
  private RecordInitList list;

  public RecordInitsExpression(NewTypename typename, RecordInitList list, int lineNumber) {
    super(lineNumber);
    this.typename = typename;
    this.list = list;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public NewTypename typename() {
    return typename;
  }

  public RecordInitList list() {
    return list;
  }
}
