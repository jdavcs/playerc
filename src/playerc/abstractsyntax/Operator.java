/*
 * This code is part of a compiler for the Player programming language
 * Created: 2005-2006
 * Revised: 09/2017
 */
package playerc.abstractsyntax;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class Operator extends AbstractSyntaxTree {
  private int type;

  public Operator(int type, int lineNumber) {
    super(lineNumber);
    this.type = type;
  }

  public int type() {
    return type;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
